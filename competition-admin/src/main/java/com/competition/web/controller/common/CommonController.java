package com.competition.web.controller.common;

import com.competition.common.config.RuoYiConfig;
import com.competition.common.constant.Constants;
import com.competition.common.core.domain.AjaxResult;
import com.competition.common.utils.FileInfoUtils;
import com.competition.common.utils.ServletUtils;
import com.competition.common.utils.StringUtils;
import com.competition.common.utils.bean.TChunkInfo;
import com.competition.common.utils.file.FileUploadUtils;
import com.competition.common.utils.file.FileUtils;
import com.competition.framework.config.ServerConfig;
import com.competition.system.domain.QueryInfo;
import com.competition.system.domain.TFileInfo;
import com.competition.system.domain.TFileInfoVO;
import com.competition.system.service.FileInfoService;
import com.competition.system.service.IChunkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用请求处理
 *
 * @author competition
 */
@RestController
@RequestMapping("/common")
public class CommonController {
	private static final Logger log = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private ServerConfig serverConfig;

	private static final String FILE_DELIMETER = ",";

	@Resource
	private IChunkService chunkService;

	@Resource
	private FileInfoService fileInfoService;

	/**
	 * 通用下载请求
	 *
	 * @param fileName 文件名称
	 * @param delete   是否删除
	 */
	@GetMapping("/downloads")
	public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
		try {
			if (!FileUtils.checkAllowDownload(fileName)) {
				throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
			}
			String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
			String filePath = RuoYiConfig.getDownloadPath() + fileName;

			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			FileUtils.setAttachmentResponseHeader(response, realFileName);
			FileUtils.writeBytes(filePath, response.getOutputStream());
			if (delete) {
				FileUtils.deleteFile(filePath);
			}
		} catch (Exception e) {
			log.error("下载文件失败", e);
		}
	}

	/**
	 * 通用上传请求（单个）
	 */
	@PostMapping("/upload")
	public AjaxResult uploadFile(MultipartFile file) throws Exception {
		try {
			// 上传文件路径
			String filePath = RuoYiConfig.getUploadPath();
			// 上传并返回新文件名称
			String fileName = FileUploadUtils.upload(filePath, file);
			String url = serverConfig.getUrl() + fileName;
			AjaxResult ajax = AjaxResult.success();
			ajax.put("url", url);
			ajax.put("fileName", fileName);
			ajax.put("newFileName", FileUtils.getName(fileName));
			ajax.put("originalFilename", file.getOriginalFilename());
			return ajax;
		} catch (Exception e) {
			return AjaxResult.error(e.getMessage());
		}
	}

	/**
	 * 上传文件块
	 *
	 * @param chunk
	 * @return
	 */
	@PostMapping("/chunk")
	public String uploadChunk(TChunkInfo chunk) {
		String apiRlt = "200";

		MultipartFile file = chunk.getUpfile();
		log.info("file originName: {}, chunkNumber: {}", file.getOriginalFilename(), chunk.getChunkNumber());

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(FileInfoUtils.generatePath(RuoYiConfig.getUploadPath(), chunk));
			//文件写入指定路径
			Files.write(path, bytes);
			if (chunkService.saveChunk(chunk) < 0) {
				apiRlt = "415";
			}

		} catch (IOException e) {
			e.printStackTrace();
			apiRlt = "415";
		}
		return apiRlt;
	}

	@GetMapping("/chunk")
	public com.competition.system.domain.UploadResult checkChunk(TChunkInfo chunk, HttpServletResponse response) {
		com.competition.system.domain.UploadResult ur = new com.competition.system.domain.UploadResult();

		//默认返回其他状态码，前端不进去checkChunkUploadedByResponse函数，正常走标准上传
		response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);

		String file = RuoYiConfig.getUploadPath() + "/" + chunk.getIdentifier() + "/" + chunk.getFilename();

		//先判断整个文件是否已经上传过了，如果是，则告诉前端跳过上传，实现秒传
		if (FileInfoUtils.fileExists(file)) {
			ur.setSkipUpload(true);
			ur.setLocation(file);
			response.setStatus(HttpServletResponse.SC_OK);
			ur.setMessage("完整文件已存在，直接跳过上传，实现秒传");
			return ur;
		}

		//如果完整文件不存在，则去数据库判断当前哪些文件块已经上传过了，把结果告诉前端，跳过这些文件块的上传，实现断点续传
		ArrayList<Integer> list = chunkService.checkChunk(chunk);
		if (list != null && list.size() > 0) {
			ur.setSkipUpload(false);
			ur.setUploadedChunks(list);
			response.setStatus(HttpServletResponse.SC_OK);
			ur.setMessage("部分文件块已存在，继续上传剩余文件块，实现断点续传");
			return ur;
		}
		return ur;
	}

	@PostMapping("/mergeFile")
	public String mergeFile(@RequestBody TFileInfoVO fileInfoVO) {

		String rlt = "FALURE";

		//前端组件参数转换为model对象
		TFileInfo fileInfo = new TFileInfo();
		fileInfo.setFilename(fileInfoVO.getName());
		fileInfo.setIdentifier(fileInfoVO.getUniqueIdentifier());
		fileInfo.setId(fileInfoVO.getId());
		fileInfo.setTotalSize(fileInfoVO.getSize());
		fileInfo.setRefProjectId(fileInfoVO.getRefProjectId());

		//进行文件的合并操作
		String filename = fileInfo.getFilename();
		String file = RuoYiConfig.getUploadPath() + "/" + fileInfo.getIdentifier() + "/" + filename;
		String folder = RuoYiConfig.getUploadPath() + "/" + fileInfo.getIdentifier();
		String fileSuccess = FileInfoUtils.merge(file, folder, filename);

		fileInfo.setLocation(file);

		//文件合并成功后，保存记录至数据库
		if ("200".equals(fileSuccess)) {
			if (fileInfoService.addFileInfo(fileInfo) > 0) rlt = "SUCCESS";
		}

		//如果已经存在，则判断是否同一个项目，同一个项目的不用新增记录，否则新增
		if ("300".equals(fileSuccess)) {
			List<TFileInfo> tfList = fileInfoService.selectFileByParams(fileInfo);
			if (tfList != null) {
				if (tfList.size() == 0 || (tfList.size() > 0 && !fileInfo.getRefProjectId().equals(tfList.get(0).getRefProjectId()))) {
					if (fileInfoService.addFileInfo(fileInfo) > 0) rlt = "SUCCESS";
				}
			}
		}

		return rlt;
	}

	/**
	 * 查询列表
	 *
	 * @return ApiResult
	 */
	@RequestMapping(value = "/selectFileList", method = RequestMethod.POST)
	public AjaxResult selectFileList(@RequestBody QueryInfo query) {
		PageHelper.startPage(query.getPageIndex(), query.getPageSize());
		List<TFileInfo> list = fileInfoService.selectFileList(query);
		PageInfo<TFileInfo> pageResult = new PageInfo<>(list);
		return AjaxResult.success(pageResult);
	}

	/**
	 * 下载文件
	 *
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest req, HttpServletResponse resp) {
		String location = req.getParameter("location");
		String fileName = req.getParameter("filename");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(location));
			fos = resp.getOutputStream();
			bos = new BufferedOutputStream(fos);
			ServletUtils.setFileDownloadHeader(req, resp, fileName);
			int byteRead = 0;
			byte[] buffer = new byte[8192];
			while ((byteRead = bis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, byteRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bos.flush();
				bis.close();
				fos.close();
				bos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public AjaxResult deleteFile(@RequestBody TFileInfo tFileInfo) {
		int result = fileInfoService.deleteFile(tFileInfo);
		return AjaxResult.success(result);
	}

	/**
	 * 通用上传请求（多个）
	 */
	@PostMapping("/uploads")
	public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception {
		try {
			// 上传文件路径
			String filePath = RuoYiConfig.getUploadPath();
			List<String> urls = new ArrayList<String>();
			List<String> fileNames = new ArrayList<String>();
			List<String> newFileNames = new ArrayList<String>();
			List<String> originalFilenames = new ArrayList<String>();
			for (MultipartFile file : files) {
				// 上传并返回新文件名称
				String fileName = FileUploadUtils.upload(filePath, file);
				String url = serverConfig.getUrl() + fileName;
				urls.add(url);
				fileNames.add(fileName);
				newFileNames.add(FileUtils.getName(fileName));
				originalFilenames.add(file.getOriginalFilename());
			}
			AjaxResult ajax = AjaxResult.success();
			ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
			ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
			ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
			ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
			return ajax;
		} catch (Exception e) {
			return AjaxResult.error(e.getMessage());
		}
	}

	/**
	 * 本地资源通用下载
	 */
	@GetMapping("/download/resource")
	public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			if (!FileUtils.checkAllowDownload(resource)) {
				throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
			}
			// 本地资源路径
			String localPath = RuoYiConfig.getProfile();
			// 数据库资源地址
			String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
			// 下载名称
			String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			FileUtils.setAttachmentResponseHeader(response, downloadName);
			FileUtils.writeBytes(downloadPath, response.getOutputStream());
		} catch (Exception e) {
			log.error("下载文件失败", e);
		}
	}
}

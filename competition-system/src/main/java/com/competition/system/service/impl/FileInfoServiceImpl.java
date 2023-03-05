package com.competition.system.service.impl;

import com.competition.common.utils.SnowflakeIdWorker;
import com.competition.system.domain.QueryInfo;
import com.competition.system.domain.TFileInfo;
import com.competition.system.mapper.TFileInfoMapper;
import com.competition.system.service.FileInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @package: com.competition.system.service.impl
 * @author: luweihong
 * @description:
 * @create: 2022/3/28 20:25
 * @version: 1.0
 */
@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Resource
	TFileInfoMapper tFileInfoMapper;

	@Override
	public int addFileInfo(TFileInfo fileInfo) {
		fileInfo.setId(SnowflakeIdWorker.getUUID()+SnowflakeIdWorker.getUUID());
		return tFileInfoMapper.insertSelective(fileInfo);
	}

	@Override
	public List<TFileInfo> selectFileByParams(TFileInfo fileInfo) {
		return tFileInfoMapper.selectFileByParams(fileInfo);
	}

	@Override
	public List<TFileInfo> selectFileList(QueryInfo query) {
		return tFileInfoMapper.selectFileList(query);
	}

	@Override
	public int deleteFile(TFileInfo tFileInfo) {
		TFileInfo t = new TFileInfo();
		t.setId(tFileInfo.getId());
		t.setDelFlag("1");
		return tFileInfoMapper.updateByPrimaryKeySelective(t);
	}
}

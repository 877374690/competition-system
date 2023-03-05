package com.competition.system.service;

import com.competition.system.domain.QueryInfo;
import com.competition.system.domain.TFileInfo;

import java.util.List;

/**
 * @package: com.competition.system.service
 * @author: luweihong
 * @description:
 * @create: 2022/3/28 20:25
 * @version: 1.0
 */
public interface FileInfoService {

	public int addFileInfo(TFileInfo fileInfo);

	public List<TFileInfo> selectFileByParams(TFileInfo fileInfo);

	/**
	 * 查询
	 *
	 * @param query 查询条件
	 * @return List
	 */
	List<TFileInfo> selectFileList(QueryInfo query);


	/**
	 * 删除
	 * @param TFileInfo
	 * @return
	 */
	int deleteFile(TFileInfo tFileInfo);
}

package com.competition.system.mapper;

import com.competition.system.domain.QueryInfo;
import com.competition.system.domain.TFileInfo;

import java.util.List;

/**
 * @package: com.competition.system.mapper
 * @author: luweihong
 * @description:
 * @create: 2022/3/28 20:26
 * @version: 1.0
 */
public interface TFileInfoMapper {
	int deleteByPrimaryKey(String id);

	int insert(TFileInfo record);

	int insertSelective(TFileInfo record);

	TFileInfo selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(TFileInfo record);

	int updateByPrimaryKey(TFileInfo record);

	List<TFileInfo> selectFileByParams(TFileInfo fileInfo);

	List<TFileInfo> selectFileList(QueryInfo query);
}

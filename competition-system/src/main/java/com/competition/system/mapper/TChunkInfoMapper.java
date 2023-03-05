package com.competition.system.mapper;


import com.competition.common.utils.bean.TChunkInfo;

import java.util.ArrayList;

/**
 * @package: com.competition.system.mapper
 * @author: luweihong
 * @description:
 * @create: 2022/3/28 20:13
 * @version: 1.0
 */
public interface TChunkInfoMapper {

	int deleteByPrimaryKey(String id);

	int insert(TChunkInfo record);

	int insertSelective(TChunkInfo record);

	TChunkInfo selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(TChunkInfo record);

	int updateByPrimaryKey(TChunkInfo record);

	ArrayList<Integer> selectChunkNumbers(TChunkInfo record);
}

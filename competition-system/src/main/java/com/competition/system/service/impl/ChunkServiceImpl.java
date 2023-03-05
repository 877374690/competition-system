package com.competition.system.service.impl;

import com.competition.common.utils.SnowflakeIdWorker;
import com.competition.common.utils.bean.TChunkInfo;
import com.competition.system.mapper.TChunkInfoMapper;
import com.competition.system.service.IChunkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @package: com.competition.system.service.impl
 * @author: luweihong
 * @description:
 * @create: 2022/3/28 20:12
 * @version: 1.0
 */
@Service
public class ChunkServiceImpl implements IChunkService {
	@Resource
	TChunkInfoMapper tChunkInfoMapper;

	@Override
	public int saveChunk(TChunkInfo chunk) {
		chunk.setId(SnowflakeIdWorker.getUUID() + SnowflakeIdWorker.getUUID());
		return tChunkInfoMapper.insertSelective(chunk);
	}

	@Override
	public ArrayList<Integer> checkChunk(TChunkInfo chunk) {
		return tChunkInfoMapper.selectChunkNumbers(chunk);
	}

	@Override
	public boolean checkChunk(String identifier, Integer chunkNumber) {
		return false;
	}
}

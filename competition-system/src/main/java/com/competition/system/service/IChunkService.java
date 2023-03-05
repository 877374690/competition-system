package com.competition.system.service;

import com.competition.common.utils.bean.TChunkInfo;

import java.util.ArrayList;

/**
 * @package: com.competition.system.service
 * @author: luweihong
 * @description:
 * @create: 2022/3/28 20:12
 * @version: 1.0
 */
public interface IChunkService {
	/**
	 * 保存文件块
	 *
	 * @param chunk
	 */
	public int saveChunk(TChunkInfo chunk);

	/**
	 * 检查文件块是否存在
	 *
	 * @param identifier
	 * @param chunkNumber
	 * @return
	 */
	boolean checkChunk(String identifier, Integer chunkNumber);

	/**
	 * 查询哪些文件块已经上传
	 * @param chunk
	 * @return
	 */
	public ArrayList<Integer> checkChunk(TChunkInfo chunk);
}

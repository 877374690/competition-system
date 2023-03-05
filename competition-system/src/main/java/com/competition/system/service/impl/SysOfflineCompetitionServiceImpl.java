package com.competition.system.service.impl;

import com.competition.common.utils.DateUtils;
import com.competition.system.domain.SysOfflineCompetition;
import com.competition.system.mapper.SysOfflineCompetitionMapper;
import com.competition.system.service.ISysOfflineCompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 线下赛事Service业务层处理
 *
 * @author competition
 * @date 2022-03-12
 */
@Service
public class SysOfflineCompetitionServiceImpl implements ISysOfflineCompetitionService {
	@Autowired
	private SysOfflineCompetitionMapper sysOfflineCompetitionMapper;

	/**
	 * 查询线下赛事
	 *
	 * @param id 线下赛事主键
	 * @return 线下赛事
	 */
	@Override
	public SysOfflineCompetition selectSysOfflineCompetitionById(Long id) {
		return sysOfflineCompetitionMapper.selectSysOfflineCompetitionById(id);
	}

	/**
	 * 查询线下赛事列表
	 *
	 * @param sysOfflineCompetition 线下赛事
	 * @return 线下赛事
	 */
	@Override
	public List<SysOfflineCompetition> selectSysOfflineCompetitionList(SysOfflineCompetition sysOfflineCompetition) {
		return sysOfflineCompetitionMapper.selectSysOfflineCompetitionList(sysOfflineCompetition);
	}

	/**
	 * 新增线下赛事
	 *
	 * @param sysOfflineCompetition 线下赛事
	 * @return 结果
	 */
	@Override
	public int insertSysOfflineCompetition(SysOfflineCompetition sysOfflineCompetition) {
		sysOfflineCompetition.setCreateTime(DateUtils.getNowDate());
		return sysOfflineCompetitionMapper.insertSysOfflineCompetition(sysOfflineCompetition);
	}

	/**
	 * 修改线下赛事
	 *
	 * @param sysOfflineCompetition 线下赛事
	 * @return 结果
	 */
	@Override
	public int updateSysOfflineCompetition(SysOfflineCompetition sysOfflineCompetition) {
		sysOfflineCompetition.setUpdateTime(DateUtils.getNowDate());
		return sysOfflineCompetitionMapper.updateSysOfflineCompetition(sysOfflineCompetition);
	}

	/**
	 * 批量删除线下赛事
	 *
	 * @param ids 需要删除的线下赛事主键
	 * @return 结果
	 */
	@Override
	public int deleteSysOfflineCompetitionByIds(Long[] ids) {
		return sysOfflineCompetitionMapper.deleteSysOfflineCompetitionByIds(ids);
	}

	/**
	 * 删除线下赛事信息
	 *
	 * @param id 线下赛事主键
	 * @return 结果
	 */
	@Override
	public int deleteSysOfflineCompetitionById(Long id) {
		return sysOfflineCompetitionMapper.deleteSysOfflineCompetitionById(id);
	}
}

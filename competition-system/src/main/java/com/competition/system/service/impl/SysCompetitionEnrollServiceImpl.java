package com.competition.system.service.impl;

import com.competition.common.utils.DateUtils;
import com.competition.system.domain.SysCompetitionApply;
import com.competition.system.domain.SysCompetitionEnroll;
import com.competition.system.mapper.SysCompetitionEnrollMapper;
import com.competition.system.service.ISysCompetitionEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报名Service业务层处理
 *
 * @author competition
 * @date 2022-03-14
 */
@Service
public class SysCompetitionEnrollServiceImpl implements ISysCompetitionEnrollService {
	@Autowired
	private SysCompetitionEnrollMapper sysCompetitionEnrollMapper;

	/**
	 * 查询报名
	 *
	 * @param id 报名主键
	 * @return 报名
	 */
	@Override
	public SysCompetitionEnroll selectSysCompetitionEnrollById(Long id) {
		return sysCompetitionEnrollMapper.selectSysCompetitionEnrollById(id);
	}

	/**
	 * 查询报名列表
	 *
	 * @param sysCompetitionEnroll 报名
	 * @return 报名
	 */
	@Override
	public List<SysCompetitionEnroll> selectSysCompetitionEnrollList(SysCompetitionEnroll sysCompetitionEnroll) {
		return sysCompetitionEnrollMapper.selectSysCompetitionEnrollList(sysCompetitionEnroll);
	}

	/**
	 * 新增报名
	 *
	 * @param sysCompetitionEnroll 报名
	 * @return 结果
	 */
	@Override
	public int insertSysCompetitionEnroll(SysCompetitionEnroll sysCompetitionEnroll) {
		sysCompetitionEnroll.setCreateTime(DateUtils.getNowDate());
		return sysCompetitionEnrollMapper.insertSysCompetitionEnroll(sysCompetitionEnroll);
	}

	/**
	 * 修改报名
	 *
	 * @param sysCompetitionEnroll 报名
	 * @return 结果
	 */
	@Override
	public int updateSysCompetitionEnroll(SysCompetitionEnroll sysCompetitionEnroll) {
		sysCompetitionEnroll.setUpdateTime(DateUtils.getNowDate());
		return sysCompetitionEnrollMapper.updateSysCompetitionEnroll(sysCompetitionEnroll);
	}

	/**
	 * 批量删除报名
	 *
	 * @param ids 需要删除的报名主键
	 * @return 结果
	 */
	@Override
	public int deleteSysCompetitionEnrollByIds(Long[] ids) {
		return sysCompetitionEnrollMapper.deleteSysCompetitionEnrollByIds(ids);
	}

	/**
	 * 删除报名信息
	 *
	 * @param id 报名主键
	 * @return 结果
	 */
	@Override
	public int deleteSysCompetitionEnrollById(Long id) {
		return sysCompetitionEnrollMapper.deleteSysCompetitionEnrollById(id);
	}

	@Override
	public List<SysCompetitionApply> listCompetition(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionEnrollMapper.listCompetition(sysCompetitionApply);
	}
}

package com.competition.system.service.impl;

import com.competition.common.utils.DateUtils;
import com.competition.system.domain.SysCompetitionApplication;
import com.competition.system.mapper.SysCompetitionApplicationMapper;
import com.competition.system.service.ISysCompetitionApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 比赛申请Service业务层处理
 *
 * @author competition
 * @date 2022-03-15
 */
@Service
public class SysCompetitionApplicationServiceImpl implements ISysCompetitionApplicationService {
	@Autowired
	private SysCompetitionApplicationMapper sysCompetitionApplicationMapper;

	/**
	 * 查询比赛申请
	 *
	 * @param id 比赛申请主键
	 * @return 比赛申请
	 */
	@Override
	public SysCompetitionApplication selectSysCompetitionApplicationById(Long id) {
		return sysCompetitionApplicationMapper.selectSysCompetitionApplicationById(id);
	}

	/**
	 * 查询比赛申请列表
	 *
	 * @param sysCompetitionApplication 比赛申请
	 * @return 比赛申请
	 */
	@Override
	public List<SysCompetitionApplication> selectSysCompetitionApplicationList(SysCompetitionApplication sysCompetitionApplication) {
		return sysCompetitionApplicationMapper.selectSysCompetitionApplicationList(sysCompetitionApplication);
	}

	/**
	 * 新增比赛申请
	 *
	 * @param sysCompetitionApplication 比赛申请
	 * @return 结果
	 */
	@Override
	public int insertSysCompetitionApplication(SysCompetitionApplication sysCompetitionApplication) {
		sysCompetitionApplication.setCreateTime(DateUtils.getNowDate());
		return sysCompetitionApplicationMapper.insertSysCompetitionApplication(sysCompetitionApplication);
	}

	/**
	 * 修改比赛申请
	 *
	 * @param sysCompetitionApplication 比赛申请
	 * @return 结果
	 */
	@Override
	public int updateSysCompetitionApplication(SysCompetitionApplication sysCompetitionApplication) {
		sysCompetitionApplication.setUpdateTime(DateUtils.getNowDate());
		return sysCompetitionApplicationMapper.updateSysCompetitionApplication(sysCompetitionApplication);
	}

	/**
	 * 批量删除比赛申请
	 *
	 * @param ids 需要删除的比赛申请主键
	 * @return 结果
	 */
	@Override
	public int deleteSysCompetitionApplicationByIds(Long[] ids) {
		return sysCompetitionApplicationMapper.deleteSysCompetitionApplicationByIds(ids);
	}

	/**
	 * 删除比赛申请信息
	 *
	 * @param id 比赛申请主键
	 * @return 结果
	 */
	@Override
	public int deleteSysCompetitionApplicationById(Long id) {
		return sysCompetitionApplicationMapper.deleteSysCompetitionApplicationById(id);
	}

	@Override
	public List selectListCompetition(String type) {
		return sysCompetitionApplicationMapper.selectListCompetition(type);
	}

	@Override
	public List<SysCompetitionApplication> listApplyResult(SysCompetitionApplication sysCompetitionApplication) {
		return sysCompetitionApplicationMapper.listApplyResult(sysCompetitionApplication);
	}

	@Override
	public List<SysCompetitionApplication> listCountApply(SysCompetitionApplication sysCompetitionApplication) {
		return sysCompetitionApplicationMapper.listCountApply(sysCompetitionApplication);
	}
}

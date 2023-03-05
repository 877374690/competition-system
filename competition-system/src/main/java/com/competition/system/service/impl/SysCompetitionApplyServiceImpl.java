package com.competition.system.service.impl;

import com.competition.common.utils.DateUtils;
import com.competition.system.domain.SysCompetitionApply;
import com.competition.system.domain.vo.SysCompetitionVo;
import com.competition.system.mapper.SysCompetitionApplyMapper;
import com.competition.system.service.ISysCompetitionApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 添加赛事Service业务层处理
 *
 * @author competition
 * @date 2022-03-12
 */
@Service
public class SysCompetitionApplyServiceImpl implements ISysCompetitionApplyService {
	@Autowired
	private SysCompetitionApplyMapper sysCompetitionApplyMapper;

	/**
	 * 查询添加赛事
	 *
	 * @param id 添加赛事主键
	 * @return 添加赛事
	 */
	@Override
	public SysCompetitionApply selectSysCompetitionApplyById(Long id) {
		return sysCompetitionApplyMapper.selectSysCompetitionApplyById(id);
	}

	/**
	 * 查询添加赛事列表
	 *
	 * @param sysCompetitionApply 添加赛事
	 * @return 添加赛事
	 */
	@Override
	public List<SysCompetitionApply> selectSysCompetitionApplyList(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionApplyMapper.selectSysCompetitionApplyList(sysCompetitionApply);
	}

	/**
	 * 新增添加赛事
	 *
	 * @param sysCompetitionApply 添加赛事
	 * @return 结果
	 */
	@Override
	public int insertSysCompetitionApply(SysCompetitionApply sysCompetitionApply) {
		sysCompetitionApply.setCreateTime(DateUtils.getNowDate());
		return sysCompetitionApplyMapper.insertSysCompetitionApply(sysCompetitionApply);
	}

	/**
	 * 修改添加赛事
	 *
	 * @param sysCompetitionApply 添加赛事
	 * @return 结果
	 */
	@Override
	public int updateSysCompetitionApply(SysCompetitionApply sysCompetitionApply) {
		sysCompetitionApply.setUpdateTime(DateUtils.getNowDate());
		return sysCompetitionApplyMapper.updateSysCompetitionApply(sysCompetitionApply);
	}

	/**
	 * 批量删除添加赛事
	 *
	 * @param ids 需要删除的添加赛事主键
	 * @return 结果
	 */
	@Override
	public int deleteSysCompetitionApplyByIds(Long[] ids) {
		return sysCompetitionApplyMapper.deleteSysCompetitionApplyByIds(ids);
	}

	/**
	 * 删除添加赛事信息
	 *
	 * @param id 添加赛事主键
	 * @return 结果
	 */
	@Override
	public int deleteSysCompetitionApplyById(Long id) {
		return sysCompetitionApplyMapper.deleteSysCompetitionApplyById(id);
	}

	@Override
	public List<SysCompetitionApply> selectuncheckList(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionApplyMapper.selectuncheckList(sysCompetitionApply);
	}

	@Override
	public List<SysCompetitionApply> getCompetitionList(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionApplyMapper.getCompetitionList(sysCompetitionApply);
	}

	@Override
	public List<SysCompetitionApply> getCompetitionList1(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionApplyMapper.getCompetitionList1(sysCompetitionApply);
	}

	@Override
	public List<SysCompetitionApply> listCancel(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionApplyMapper.listCancel(sysCompetitionApply);
	}

	@Override
	public List<SysCompetitionApply> getWorkLists(SysCompetitionApply sysCompetitionApply) {
		return sysCompetitionApplyMapper.getWorkLists(sysCompetitionApply);
	}

	@Override
	public List<SysCompetitionVo> selectSysAnswerList() {
		return sysCompetitionApplyMapper.selectSysAnswerList();
	}
}

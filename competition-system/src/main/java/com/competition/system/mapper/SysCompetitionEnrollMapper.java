package com.competition.system.mapper;

import com.competition.system.domain.SysCompetitionApply;
import com.competition.system.domain.SysCompetitionEnroll;

import java.util.List;

/**
 * 报名Mapper接口
 *
 * @author competition
 * @date 2022-03-14
 */
public interface SysCompetitionEnrollMapper {
	/**
	 * 查询报名
	 *
	 * @param id 报名主键
	 * @return 报名
	 */
	public SysCompetitionEnroll selectSysCompetitionEnrollById(Long id);

	/**
	 * 查询报名列表
	 *
	 * @param sysCompetitionEnroll 报名
	 * @return 报名集合
	 */
	public List<SysCompetitionEnroll> selectSysCompetitionEnrollList(SysCompetitionEnroll sysCompetitionEnroll);

	/**
	 * 新增报名
	 *
	 * @param sysCompetitionEnroll 报名
	 * @return 结果
	 */
	public int insertSysCompetitionEnroll(SysCompetitionEnroll sysCompetitionEnroll);

	/**
	 * 修改报名
	 *
	 * @param sysCompetitionEnroll 报名
	 * @return 结果
	 */
	public int updateSysCompetitionEnroll(SysCompetitionEnroll sysCompetitionEnroll);

	/**
	 * 删除报名
	 *
	 * @param id 报名主键
	 * @return 结果
	 */
	public int deleteSysCompetitionEnrollById(Long id);

	/**
	 * 批量删除报名
	 *
	 * @param ids 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteSysCompetitionEnrollByIds(Long[] ids);

	List<SysCompetitionApply> listCompetition(SysCompetitionApply sysCompetitionApply);
}

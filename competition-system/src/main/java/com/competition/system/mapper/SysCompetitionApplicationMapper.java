package com.competition.system.mapper;

import com.competition.system.domain.SysCompetitionApplication;

import java.util.List;

/**
 * 比赛申请Mapper接口
 * 
 * @author competition
 * @date 2022-03-15
 */
public interface SysCompetitionApplicationMapper 
{
    /**
     * 查询比赛申请
     * 
     * @param id 比赛申请主键
     * @return 比赛申请
     */
    public SysCompetitionApplication selectSysCompetitionApplicationById(Long id);

    /**
     * 查询比赛申请列表
     * 
     * @param sysCompetitionApplication 比赛申请
     * @return 比赛申请集合
     */
    public List<SysCompetitionApplication> selectSysCompetitionApplicationList(SysCompetitionApplication sysCompetitionApplication);

    /**
     * 新增比赛申请
     * 
     * @param sysCompetitionApplication 比赛申请
     * @return 结果
     */
    public int insertSysCompetitionApplication(SysCompetitionApplication sysCompetitionApplication);

    /**
     * 修改比赛申请
     * 
     * @param sysCompetitionApplication 比赛申请
     * @return 结果
     */
    public int updateSysCompetitionApplication(SysCompetitionApplication sysCompetitionApplication);

    /**
     * 删除比赛申请
     * 
     * @param id 比赛申请主键
     * @return 结果
     */
    public int deleteSysCompetitionApplicationById(Long id);

    /**
     * 批量删除比赛申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCompetitionApplicationByIds(Long[] ids);

    List selectListCompetition(String type);

    List<SysCompetitionApplication> listApplyResult(SysCompetitionApplication sysCompetitionApplication);

    List<SysCompetitionApplication> listCountApply(SysCompetitionApplication sysCompetitionApplication);
}

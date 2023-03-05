package com.competition.system.service;

import com.competition.system.domain.SysOfflineCompetition;

import java.util.List;

/**
 * 线下赛事Service接口
 * 
 * @author competition
 * @date 2022-03-12
 */
public interface ISysOfflineCompetitionService 
{
    /**
     * 查询线下赛事
     * 
     * @param id 线下赛事主键
     * @return 线下赛事
     */
    public SysOfflineCompetition selectSysOfflineCompetitionById(Long id);

    /**
     * 查询线下赛事列表
     * 
     * @param sysOfflineCompetition 线下赛事
     * @return 线下赛事集合
     */
    public List<SysOfflineCompetition> selectSysOfflineCompetitionList(SysOfflineCompetition sysOfflineCompetition);

    /**
     * 新增线下赛事
     * 
     * @param sysOfflineCompetition 线下赛事
     * @return 结果
     */
    public int insertSysOfflineCompetition(SysOfflineCompetition sysOfflineCompetition);

    /**
     * 修改线下赛事
     * 
     * @param sysOfflineCompetition 线下赛事
     * @return 结果
     */
    public int updateSysOfflineCompetition(SysOfflineCompetition sysOfflineCompetition);

    /**
     * 批量删除线下赛事
     * 
     * @param ids 需要删除的线下赛事主键集合
     * @return 结果
     */
    public int deleteSysOfflineCompetitionByIds(Long[] ids);

    /**
     * 删除线下赛事信息
     * 
     * @param id 线下赛事主键
     * @return 结果
     */
    public int deleteSysOfflineCompetitionById(Long id);
}

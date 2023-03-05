package com.competition.system.service;

import com.competition.system.domain.SysCompetitionQuestion;

import java.util.List;

/**
 * 题型设置Service接口
 * 
 * @author competition
 * @date 2022-03-13
 */
public interface ISysCompetitionQuestionService 
{
    /**
     * 查询题型设置
     * 
     * @param id 题型设置主键
     * @return 题型设置
     */
    public SysCompetitionQuestion selectSysCompetitionQuestionById(Long id);

    /**
     * 查询题型设置列表
     * 
     * @param sysCompetitionQuestion 题型设置
     * @return 题型设置集合
     */
    public List<SysCompetitionQuestion> selectSysCompetitionQuestionList(SysCompetitionQuestion sysCompetitionQuestion);

    /**
     * 新增题型设置
     * 
     * @param sysCompetitionQuestion 题型设置
     * @return 结果
     */
    public int insertSysCompetitionQuestion(SysCompetitionQuestion sysCompetitionQuestion);

    /**
     * 修改题型设置
     * 
     * @param sysCompetitionQuestion 题型设置
     * @return 结果
     */
    public int updateSysCompetitionQuestion(SysCompetitionQuestion sysCompetitionQuestion);

    /**
     * 批量删除题型设置
     * 
     * @param ids 需要删除的题型设置主键集合
     * @return 结果
     */
    public int deleteSysCompetitionQuestionByIds(Long[] ids);

    /**
     * 删除题型设置信息
     * 
     * @param id 题型设置主键
     * @return 结果
     */
    public int deleteSysCompetitionQuestionById(Long id);
}

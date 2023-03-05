package com.competition.system.service.impl;

import com.competition.system.domain.SysCompetitionQuestion;
import com.competition.system.mapper.SysCompetitionQuestionMapper;
import com.competition.system.service.ISysCompetitionQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题型设置Service业务层处理
 * 
 * @author competition
 * @date 2022-03-13
 */
@Service
public class SysCompetitionQuestionServiceImpl implements ISysCompetitionQuestionService 
{
    @Autowired
    private SysCompetitionQuestionMapper sysCompetitionQuestionMapper;

    /**
     * 查询题型设置
     * 
     * @param id 题型设置主键
     * @return 题型设置
     */
    @Override
    public SysCompetitionQuestion selectSysCompetitionQuestionById(Long id)
    {
        return sysCompetitionQuestionMapper.selectSysCompetitionQuestionById(id);
    }

    /**
     * 查询题型设置列表
     * 
     * @param sysCompetitionQuestion 题型设置
     * @return 题型设置
     */
    @Override
    public List<SysCompetitionQuestion> selectSysCompetitionQuestionList(SysCompetitionQuestion sysCompetitionQuestion)
    {
        return sysCompetitionQuestionMapper.selectSysCompetitionQuestionList(sysCompetitionQuestion);
    }

    /**
     * 新增题型设置
     * 
     * @param sysCompetitionQuestion 题型设置
     * @return 结果
     */
    @Override
    public int insertSysCompetitionQuestion(SysCompetitionQuestion sysCompetitionQuestion)
    {
        return sysCompetitionQuestionMapper.insertSysCompetitionQuestion(sysCompetitionQuestion);
    }

    /**
     * 修改题型设置
     * 
     * @param sysCompetitionQuestion 题型设置
     * @return 结果
     */
    @Override
    public int updateSysCompetitionQuestion(SysCompetitionQuestion sysCompetitionQuestion)
    {
        return sysCompetitionQuestionMapper.updateSysCompetitionQuestion(sysCompetitionQuestion);
    }

    /**
     * 批量删除题型设置
     * 
     * @param ids 需要删除的题型设置主键
     * @return 结果
     */
    @Override
    public int deleteSysCompetitionQuestionByIds(Long[] ids)
    {
        return sysCompetitionQuestionMapper.deleteSysCompetitionQuestionByIds(ids);
    }

    /**
     * 删除题型设置信息
     * 
     * @param id 题型设置主键
     * @return 结果
     */
    @Override
    public int deleteSysCompetitionQuestionById(Long id)
    {
        return sysCompetitionQuestionMapper.deleteSysCompetitionQuestionById(id);
    }
}

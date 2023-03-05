package com.competition.system.mapper;

import com.competition.system.domain.SysCompetitionApply;
import com.competition.system.domain.vo.SysCompetitionVo;

import java.util.List;

/**
 * 添加赛事Mapper接口
 * 
 * @author competition
 * @date 2022-03-12
 */
public interface SysCompetitionApplyMapper 
{
    /**
     * 查询添加赛事
     * 
     * @param id 添加赛事主键
     * @return 添加赛事
     */
    public SysCompetitionApply selectSysCompetitionApplyById(Long id);

    /**
     * 查询添加赛事列表
     * 
     * @param sysCompetitionApply 添加赛事
     * @return 添加赛事集合
     */
    public List<SysCompetitionApply> selectSysCompetitionApplyList(SysCompetitionApply sysCompetitionApply);

    /**
     * 新增添加赛事
     * 
     * @param sysCompetitionApply 添加赛事
     * @return 结果
     */
    public int insertSysCompetitionApply(SysCompetitionApply sysCompetitionApply);

    /**
     * 修改添加赛事
     * 
     * @param sysCompetitionApply 添加赛事
     * @return 结果
     */
    public int updateSysCompetitionApply(SysCompetitionApply sysCompetitionApply);

    /**
     * 删除添加赛事
     * 
     * @param id 添加赛事主键
     * @return 结果
     */
    public int deleteSysCompetitionApplyById(Long id);

    /**
     * 批量删除添加赛事
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCompetitionApplyByIds(Long[] ids);

    List<SysCompetitionApply> selectuncheckList(SysCompetitionApply sysCompetitionApply);

    List<SysCompetitionApply> getCompetitionList(SysCompetitionApply sysCompetitionApply);

    List<SysCompetitionApply> getCompetitionList1(SysCompetitionApply sysCompetitionApply);

    List<SysCompetitionApply> listCancel(SysCompetitionApply sysCompetitionApply);

    List<SysCompetitionApply> getWorkLists(SysCompetitionApply sysCompetitionApply);

	List<SysCompetitionVo> selectSysAnswerList();
}

package com.competition.system.mapper;

import java.util.List;

import com.competition.system.domain.SysRoleDept;

/**
 * 角色与学校关联表 数据层
 * 
 * @author competition
 */
public interface SysRoleDeptMapper
{
    /**
     * 通过角色ID删除角色和学校关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleDeptByRoleId(Long roleId);

    /**
     * 批量删除角色学校关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleDept(Long[] ids);

    /**
     * 查询学校使用数量
     * 
     * @param deptId 学校ID
     * @return 结果
     */
    public int selectCountRoleDeptByDeptId(Long deptId);

    /**
     * 批量新增角色学校信息
     * 
     * @param roleDeptList 角色学校列表
     * @return 结果
     */
    public int batchRoleDept(List<SysRoleDept> roleDeptList);
}

package com.competition.system.service;

import com.competition.common.core.domain.TreeSelect;
import com.competition.common.core.domain.entity.SysDept;

import java.util.List;

/**
 * 学校管理 服务层
 * 
 * @author competition
 */
public interface ISysDeptService
{
    /**
     * 查询学校管理数据
     * 
     * @param dept 学校信息
     * @return 学校信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 构建前端所需要树结构
     * 
     * @param depts 学校列表
     * @return 树结构列表
     */
    public List<SysDept> buildDeptTree(List<SysDept> depts);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param depts 学校列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts);

    /**
     * 根据角色ID查询学校树信息
     * 
     * @param roleId 角色ID
     * @return 选中学校列表
     */
    public List<Long> selectDeptListByRoleId(Long roleId);

    /**
     * 根据学校ID查询信息
     * 
     * @param deptId 学校ID
     * @return 学校信息
     */
    public SysDept selectDeptById(Long deptId);

    /**
     * 根据ID查询所有子学校（正常状态）
     * 
     * @param deptId 学校ID
     * @return 子学校数
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * 是否存在学校子节点
     * 
     * @param deptId 学校ID
     * @return 结果
     */
    public boolean hasChildByDeptId(Long deptId);

    /**
     * 查询学校是否存在用户
     * 
     * @param deptId 学校ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 校验学校名称是否唯一
     * 
     * @param dept 学校信息
     * @return 结果
     */
    public String checkDeptNameUnique(SysDept dept);

    /**
     * 校验学校是否有数据权限
     * 
     * @param deptId 学校id
     */
    public void checkDeptDataScope(Long deptId);

    /**
     * 新增保存学校信息
     * 
     * @param dept 学校信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改保存学校信息
     * 
     * @param dept 学校信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 删除学校管理信息
     * 
     * @param deptId 学校ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);
}

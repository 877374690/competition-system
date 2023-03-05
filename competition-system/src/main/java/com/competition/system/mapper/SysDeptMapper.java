package com.competition.system.mapper;

import com.competition.common.core.domain.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学校管理 数据层
 * 
 * @author competition
 */
public interface SysDeptMapper
{
    /**
     * 查询学校管理数据
     * 
     * @param dept 学校信息
     * @return 学校信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 根据角色ID查询学校树信息
     * 
     * @param roleId 角色ID
     * @param deptCheckStrictly 学校树选择项是否关联显示
     * @return 选中学校列表
     */
    public List<Long> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);

    /**
     * 根据学校ID查询信息
     * 
     * @param deptId 学校ID
     * @return 学校信息
     */
    public SysDept selectDeptById(Long deptId);

    /**
     * 根据ID查询所有子学校
     * 
     * @param deptId 学校ID
     * @return 学校列表
     */
    public List<SysDept> selectChildrenDeptById(Long deptId);

    /**
     * 根据ID查询所有子学校（正常状态）
     * 
     * @param deptId 学校ID
     * @return 子学校数
     */
    public int selectNormalChildrenDeptById(Long deptId);

    /**
     * 是否存在子节点
     * 
     * @param deptId 学校ID
     * @return 结果
     */
    public int hasChildByDeptId(Long deptId);

    /**
     * 查询学校是否存在用户
     * 
     * @param deptId 学校ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 校验学校名称是否唯一
     * 
     * @param deptName 学校名称
     * @param parentId 父学校ID
     * @return 结果
     */
    public SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 新增学校信息
     * 
     * @param dept 学校信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改学校信息
     * 
     * @param dept 学校信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 修改所在学校正常状态
     * 
     * @param deptIds 学校ID组
     */
    public void updateDeptStatusNormal(Long[] deptIds);

    /**
     * 修改子元素关系
     * 
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 删除学校管理信息
     * 
     * @param deptId 学校ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);
}

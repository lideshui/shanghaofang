package com.atguigu.dao;

import com.atguigu.entity.RolePermission;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RolePermissionDao
 */
public interface RolePermissionDao extends BaseDao<RolePermission> {
    /**
     * 根据角色Id查询到所有权限Id，为了将其设置为选中状态
     */
    List<Long> findPermissionIdByRoleId(Long roleId);

    /**
     * 根据角色id删除所有的权限Id
     */
    void deleteByRoleId(Long roleId);


}
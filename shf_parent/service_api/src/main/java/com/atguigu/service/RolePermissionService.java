package com.atguigu.service;

import com.atguigu.entity.RolePermission;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RolePermissionService
 */
public interface RolePermissionService extends BaseService<RolePermission> {

    /**
     * 更新角色权限
     */
    void insertRolePermission(Long roleId,Long[] permissionIds);

}
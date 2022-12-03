package com.atguigu.service;

import com.atguigu.entity.AdminRole;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.AdminRoleService
 */
public interface AdminRoleService extends BaseService<AdminRole> {

    /**
     * 为当前用户添加多个角色
     */
    void insertAdminRole(Long adminId,Long[] roleIds);
}
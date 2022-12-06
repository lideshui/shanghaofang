package com.atguigu.service;

import com.atguigu.entity.Admin;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface AdminService extends BaseService<Admin> {
    /**
     * 后台登录验证，根据用户名获取用户信息
     */
    Admin findAdminByUsername(String username);
}
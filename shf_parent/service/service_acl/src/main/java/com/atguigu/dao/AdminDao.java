package com.atguigu.dao;

import com.atguigu.entity.Admin;



/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface AdminDao extends BaseDao<Admin> {
    /**
     * 后台登录验证，根据用户名获取用户信息
     */
    Admin findAdminByUsername(String username);
}
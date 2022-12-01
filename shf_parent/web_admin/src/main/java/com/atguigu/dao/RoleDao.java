package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao {
    List<Role> findAll();
}
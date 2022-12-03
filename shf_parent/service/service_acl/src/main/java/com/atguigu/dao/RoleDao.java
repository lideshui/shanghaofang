package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao extends BaseDao<Role> {

    /**
     * 获取所有角色信息
     */
    List<Role> findAll();

}
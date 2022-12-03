package com.atguigu.dao;

import com.atguigu.entity.AdminRole;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.AdminRoleDao
 */
public interface AdminRoleDao extends BaseDao<AdminRole> {

    /**
     * 根据用户id获取所有角色id值集合
     */
    List<Long> findRoleIdsByAdminId(Long adminId);

    /**
     * 删除当前用户的所有角色
     */
    void deleteByAdminId(Long adminId);
}
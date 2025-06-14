package com.atguigu.dao;

import com.atguigu.entity.Permission;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.PermissionDao
 */
public interface PermissionDao extends BaseDao<Permission> {
    /**
     * 查询出所有权限菜单，加工后用插件渲染
     */
    List<Permission> findAll();

    /**
     * 获取当前用户的权限菜单，然后循环渲染
     */
    List<Permission> findPermissionByAdminId(Long adminId);

    /**
     * 获取自己的子节点列表
     */
    List<Permission> findPermissionByParentId(Serializable parentId);

    /**
     * 根据当前登录用户的id获取全部的按钮权限code
     */
    List<String> findPermissionCodeByAdminId(Long adminId);

    /**
     * 直接获取全部的权限code，为超级管理员提供全部按钮权限时使用
     */
    List<String> findAllCode();
}
package com.atguigu.dao;

import com.atguigu.entity.Permission;
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
}
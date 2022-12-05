package com.atguigu.service;

import com.atguigu.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.PermissionService
 */
public interface PermissionService extends BaseService<Permission> {

    /**
     * 返回渲染菜单指定格式的数据
     */
    List<Map<String,Object>> findZNodes(Long roleId);

    /**
     * 获取当前用户的菜单权限，获取后循环渲染
     */
    List<Permission> findPermissionByAdminId(Long adminId);
}
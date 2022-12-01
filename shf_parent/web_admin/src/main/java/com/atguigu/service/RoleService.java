package com.atguigu.service;

import com.atguigu.entity.Role;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService {
    /**
     * @Description: 角色列表
     */
    List<Role> findAll();

    /**
     * @Description: 角色新增
     */
    Integer insert(Role role);

    /**
     * @Description: 修改回显
     */
    Role getById(Long id);

    /**
     * @Description: 修改操作
     */
    Integer update(Role role);

    /**
     * @Description: 删除操作
     */
    void delete(Long id);
}
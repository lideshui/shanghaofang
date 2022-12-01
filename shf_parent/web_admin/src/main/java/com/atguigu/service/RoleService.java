package com.atguigu.service;

import com.atguigu.entity.Role;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService {
    /**
     * @Description: index页搜索条件，可替代查询所有
     */
    List<Role> findRole(Map<String,Object> filters);

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
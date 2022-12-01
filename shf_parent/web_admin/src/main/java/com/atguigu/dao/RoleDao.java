package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao {
    /**
     * @Description: index页搜索条件，可替代查询所有
     */
    List<Role> findRole(Map<String,Object> filters);

    /**
     * @Description: 插入一条数据
     */
    Integer insert(Role role);

    /**
     * @Description: 通过id获取
     */
    Role getById(Long id);

    /**
     * @Description: 修改数据
     */
    Integer update(Role role);

    /**
     * @Description: 删除数据
     */
    void delete(Long id);
}
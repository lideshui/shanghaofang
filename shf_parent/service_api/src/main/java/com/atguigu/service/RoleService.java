package com.atguigu.service;

import com.atguigu.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 查询该用户已拥有和未拥有的角色列表
     * 使用集合作为返回值，可以一次性返回已拥有和未拥有两个不同的角色列表
     */
    Map<String, List<Role>> findRoleByAdminId(Long adminId);

}
package com.atguigu.service.impl;

import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * @Description: 条件搜索，可代替查询所有角色
     */
    @Override
    public List<Role> findRole(Map<String, Object> filters) {
        return  roleDao.findRole(filters);
    }

    /**
     * @Description: 新增角色
     */
    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    /**
     * @Description: 修改角色回显数据
     */
    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    /**
     * @Description: 角色更新操作
     */
    @Override
    public Integer update(Role role) {
        return roleDao.update(role);
    }

    /**
     * @Description: 角色删除操作
     */
    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
}
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.RolePermissionDao;
import com.atguigu.entity.RolePermission;
import com.atguigu.service.RolePermissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RolePermissionServiceImpl
 */
@DubboService
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public BaseDao<RolePermission> getEntityDao() {
        return rolePermissionDao;
    }

    /**
     * 更新角色权限菜单，先删除当前所有的权限菜单，再新增本次设置的
     */
    @Override
    @Transactional
    public void insertRolePermission(Long roleId, Long[] permissionIds) {
        //1. 根据roleId将目前的所有关系删除
        rolePermissionDao.deleteByRoleId(roleId);
        //2. 再重新循环新增权限
        for (Long permissionId : permissionIds) {
            RolePermission rolePermission=new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionDao.insert(rolePermission);
        }
    }
}
package com.atguigu.service.impl;

import com.atguigu.dao.AdminRoleDao;
import com.atguigu.dao.BaseDao;
import com.atguigu.entity.AdminRole;
import com.atguigu.service.AdminRoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.AdminRoleServiceImpl
 */
@DubboService
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRole> implements AdminRoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    public BaseDao<AdminRole> getEntityDao() {
        return adminRoleDao;
    }

    /**
     * 为当前用户添加角色
     * 添加步骤：先全部删除之前设置的角色，再循环新增现在的角色
     */
    @Override
    @Transactional
    public void insertAdminRole(Long adminId, Long[] roleIds) {
        //1. 先将adminId的所有角色删除
        adminRoleDao.deleteByAdminId(adminId);
        //2. 在循环添加新的角色信息
        for (Long roleId : roleIds) {
            if (roleId == null)
                continue;
            //创建实例对象并赋值，为新增作准备
            AdminRole adminRole = new AdminRole();
            adminRole.setRoleId(roleId);
            adminRole.setAdminId(adminId);
            adminRoleDao.insert(adminRole);
        }
    }
}
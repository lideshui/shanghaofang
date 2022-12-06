package com.atguigu.service.impl;

import com.atguigu.dao.AdminDao;
import com.atguigu.dao.BaseDao;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@DubboService
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public BaseDao<Admin> getEntityDao() {
        return adminDao;
    }

    /**
     * 后台登录验证，根据用户名获取用户信息
     */
    @Override
    public Admin findAdminByUsername(String username) {
        return adminDao.findAdminByUsername(username);
    }
}
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleDao roleDao;


    @Override
    public BaseDao<Role> getEntityDao() {
        return roleDao;
    }
}
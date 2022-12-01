package com.atguigu.service.impl;

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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * @Description: 搜索结果分页显示
     */
    @Override
    public PageInfo<Role> findPage(Map<String, Object> filters) {
        //当前页数，common-util引入工具类：CastUtil
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);

        //分页插件
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleDao.findPage(filters);
        //构造方法中传入list集合和每页显示条数
        //通过pageInfo.getList()可获取list集合的数据，将pageInfo对象放到请求域就可以共享到前端
        PageInfo pageInfo = new PageInfo<Role>(list, 3);
        return pageInfo;
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
package com.atguigu.service.impl;

import com.atguigu.dao.AdminRoleDao;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@DubboService
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AdminRoleDao adminRoleDao;


    @Override
    public BaseDao<Role> getEntityDao() {
        return roleDao;
    }

    /**
     * 查询该用户已拥有和未拥有的角色列表
     * 使用集合作为返回值，可以一次性返回已拥有和未拥有两个不同的角色列表
     */
    @Override
    public Map<String, List<Role>> findRoleByAdminId(Long adminId) {
        //1. 查询出所有的角色信息
        List<Role> roleList = roleDao.findAll();
        //2. 查询出当前用户拥有的角色id
        List<Long> roleIds = adminRoleDao.findRoleIdsByAdminId(adminId);
        //3. 循环所有的角色信息
        List<Role> noAssignRoleList=new ArrayList<>();
        List<Role> assignRoleList=new ArrayList<>();
        for (Role role : roleList) {
            //判断role的id是都在roleIds内存在
            if(roleIds.contains(role.getId())){
                //说明role是当前用户已拥有的角色
                assignRoleList.add(role);
            }else{
                //说明role是当前用户未拥有的角色
                noAssignRoleList.add(role);
            }
        }
        Map<String,List<Role>> map=new HashMap<>();
        //存储未拥有的角色列表
        map.put("noAssignRoleList",noAssignRoleList);
        //存储已拥有的角色列表
        map.put("assignRoleList",assignRoleList);
        return map;
    }
}
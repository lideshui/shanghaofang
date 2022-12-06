package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.PermissionDao;
import com.atguigu.dao.RolePermissionDao;
import com.atguigu.entity.Permission;
import com.atguigu.service.PermissionService;
import com.atguigu.util.PermissionHelper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.PermissionServiceImpl
 */
@DubboService
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public BaseDao<Permission> getEntityDao() {
        return permissionDao;
    }


    /**
     * 先查询出所有的权限菜单，经过处理变为满足插件渲染的格式数据，再返回给前端
     */
    @Override
    public List<Map<String, Object>> findZNodes(Long roleId) {
        //先查出所有的权限菜单
        List<Permission> list = permissionDao.findAll();

        //再查出当前角色已拥有的权限菜单，为了将其设置为选中状态
        List<Long> permissionIdList = rolePermissionDao.findPermissionIdByRoleId(roleId);

        //将权限菜单list加工为插件可以渲染的格式
        List<Map<String,Object>> zNodes=new ArrayList<>();
        for (Permission permission : list) {
            Map<String,Object> map=new HashMap<>();
            //{ id:1, pId:0, name:"", checked:true}
            map.put("id",permission.getId());
            map.put("pId",permission.getParentId());
            map.put("name",permission.getName());
            //是否选中，当前角色已拥有的权限菜单，将其设置为选中状态
            if(permissionIdList.contains(permission.getId())){
                map.put("checked",true);
            }
            //每一个map都放到list集合里
            zNodes.add(map);
        }
        return zNodes;
    }

    /**
     * 先拿到当前用户的所有权限菜单信息，再通过工具类PermissionHelper处理菜单的级别关系
     * PermissionHelper类底层是通过递归来处理分级关系的，因为是服务器渲染的，处理后才可以循环渲染
     */
    @Override
    public List<Permission> findPermissionByAdminId(Long adminId) {
        List<Permission> permissionList = permissionDao.findPermissionByAdminId(adminId);
        //permissionList所有的菜单信息，需要借助PermissionHelper处理其分级关系
        List<Permission> permissionList1 = PermissionHelper.bulid(permissionList);
        return permissionList1;
    }

    /**
     * 获取所有的权限菜单节点，并通过PermissionHelper类递归来处理分级关系
     */
    @Override
    public List<Permission> findAll() {
        List<Permission> list = permissionDao.findAll();
        return PermissionHelper.bulid(list);
    }

    /**
     * 重写基类中的删除方法，递归删除节点和其所有子节点
     */
    @Override
    public void delete(Serializable id) {
        //获取自己的子节点，递归删除
        List<Permission> permissionList = permissionDao.findPermissionByParentId(id);
        //判断有无子节点，若有子节点则进行递归删除
        if(permissionList!=null && permissionList.size()!=0){
            //迭代递归删除子节点
            for (Permission permission : permissionList) {
                delete(permission.getId());
            }
        }
        //删除子节点后，再删除自身节点
        permissionDao.delete(id);
    }
}
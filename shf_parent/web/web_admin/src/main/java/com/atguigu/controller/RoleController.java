package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.PermissionService;
import com.atguigu.service.RolePermissionService;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @DubboReference
    private RoleService roleService;

    @DubboReference
    private PermissionService permissionService;

    @DubboReference
    private RolePermissionService rolePermissionService;

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";


    /**
     * @Description: 处理/role请求，搜索处理、分页处理
     */
    @PreAuthorize("hasAuthority('role.show')")
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Role> page = roleService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * @Description: 处理/create请求，跳转到添加角色页面
     */
    @PreAuthorize("hasAuthority('role.create')")
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * @Description: 处理/save请求，执行添加角色操作
     */
    @PreAuthorize("hasAuthority('role.create')")
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/edit/id请求，跳转到修改角色页面
     */
    @PreAuthorize("hasAuthority('role.edit')")
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

    /**
     * @Description: 处理/update请求，执行角色修改操作
     */
    @PreAuthorize("hasAuthority('role.edit')")
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/delete/id请求，执行角色删除操作
     */
    @PreAuthorize("hasAuthority('role.delete')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

    /**
     * 处理/assignShow/roleId路径，获取角色权限列表
     */
    @PreAuthorize("hasAuthority('role.assgin')")
    public String assignShow(@PathVariable Long roleId,Map map){
        //传入当前角色Id
        map.put("roleId",roleId);
        //获取指定格式的菜单渲染数据，并放到请求域
        //[{ id:2, pId:0, name:"用户管理", checked:true},{},{}...]
        List<Map<String, Object>> zNodes = permissionService.findZNodes(roleId);
        map.put("zNodes",zNodes);
        return "role/assignShow";
    }

    /**
     * 处理/assignPermission路径的请求，更新角色权限
     */
    @PreAuthorize("hasAuthority('role.assgin')")
    @RequestMapping("/assignPermission")
    public String assignPermission(Long roleId,Long[] permissionIds){
        rolePermissionService.insertRolePermission(roleId,permissionIds);
        return "common/success";
    }

}

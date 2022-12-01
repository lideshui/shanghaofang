package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 处理/role请求，查询所有角色
     */
    @RequestMapping
    public String findAll(Map map){
        //1. 调用业务层处理业务
        List<Role> list = roleService.findAll();
        //2. 将数据共享到请求域
        map.put("list",list);
        //3. 设置逻辑视图
        return PAGE_INDEX;
    }

    /**
     * @Description: 处理/create请求，跳转到添加角色页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * @Description: 处理/save请求，执行添加角色操作
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/edit/id请求，跳转到修改角色页面
     */
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
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/delete/id请求，执行角色删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

}

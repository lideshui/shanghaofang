package com.atguigu.controller;

import com.atguigu.entity.Permission;
import com.atguigu.service.PermissionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.PermissionController
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {


    @DubboReference
    private PermissionService permissionService;

    private final static String LIST_ACTION = "redirect:/permission";
    private final static String PAGE_INDEX = "permission/index";
    private final static String PAGE_CREATE = "permission/create";
    private final static String PAGE_EDIT = "permission/edit";
    private final static String PAGE_SUCCESS = "common/success";


    /**
     * 获取菜单
     */
    @RequestMapping
    public String index(Map map){
        List<Permission> list = permissionService.findAll();
        map.put("list",list);
        return PAGE_INDEX;
    }

    /**
     * 处理/create请求，跳转到新增页面
     */
    @RequestMapping("/create")
    public String create(Permission permission,Map map){
        map.put("permission",permission);
        return PAGE_CREATE;
    }

    /**
     * 处理/save请求，保存新增
     */
    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.insert(permission);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/edit/id操作，跳转到编辑页面
     */
    @RequestMapping("/edit/{permissionId}")
    public String edit(@PathVariable Long permissionId, Map map){
        Permission permission = permissionService.getById(permissionId);
        map.put("permission",permission);
        return PAGE_EDIT;
    }

    /**
     * 处理/update请求，保存更新
     */
    @RequestMapping("/update")
    public String update(Permission permission){
        permissionService.update(permission);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/delete/id请求，删除操作
     */
    @RequestMapping("/delete/{permissionId}")
    public String delete(@PathVariable Long permissionId){
        permissionService.delete(permissionId);
        return LIST_ACTION;
    }
}
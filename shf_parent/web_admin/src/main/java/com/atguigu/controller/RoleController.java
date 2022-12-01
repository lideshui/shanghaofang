package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private RoleService roleService;

    @RequestMapping
    public String findAll(Map map){
        //1. 调用业务层处理业务
        List<Role> list = roleService.findAll();
        //2. 将数据共享到请求域
        map.put("list",list);
        //3. 设置逻辑视图
        return PAGE_INDEX;
    }

}

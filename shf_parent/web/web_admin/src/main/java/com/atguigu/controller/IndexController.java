package com.atguigu.controller;

import com.atguigu.entity.Admin;
import com.atguigu.entity.Permission;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.IndexController
 */
@Controller
public class IndexController {

    @DubboReference
    private AdminService adminService;

    @DubboReference
    private PermissionService permissionService;

    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";

    /**
     * 首页默认访问路径
     */
    @RequestMapping("/")
    public String index(Map map) {
        //先写死，因为还没做后台的登录功能，先模拟登录用户的id
        Long adminId = 3L;
        Admin admin = adminService.getById(adminId);
        List<Permission> permissionList = permissionService.findPermissionByAdminId(adminId);
        map.put("admin", admin);
        map.put("permissionList",permissionList);
        return PAGE_INDEX;
    }

    /**
     * 首页iframe窗体中内置的欢迎页面
     */
    @RequestMapping("/main")
    public String main() {
        return PAGE_MAIN;
    }

}

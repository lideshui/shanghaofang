package com.atguigu.controller;

import com.atguigu.entity.Admin;
import com.atguigu.entity.Permission;
import com.atguigu.service.AdminService;
import com.atguigu.service.PermissionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    private final static String PAGE_LOGIN = "frame/login";

    /**
     * 后台首页
     */
    @RequestMapping("/")
    public String index(Map map) {
        //获取SpringSecurity提供的User对象，即当前登录对象的加工后的认证对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        //通过认证对象的用户名获取现在登录的Admin对象
        Admin admin = adminService.findAdminByUsername(user.getUsername());
        map.put("admin", admin);

        //根据当前登录对象Id寻找对应的权限菜单，放到请求域根据登录用户的权限进行左侧菜单动态渲染
        List<Permission> permissionList = permissionService.findPermissionByAdminId(admin.getId());
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

    /**
     * 控制SpringSecurity中设置的登录请求/login所去的页面
     */
    @RequestMapping("/login")
    public String login(){
        return PAGE_LOGIN;
    }

}

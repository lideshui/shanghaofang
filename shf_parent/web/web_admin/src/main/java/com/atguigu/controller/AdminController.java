package com.atguigu.controller;

import com.atguigu.entity.Admin;
import com.atguigu.entity.Role;
import com.atguigu.service.AdminRoleService;
import com.atguigu.service.AdminService;
import com.atguigu.service.RoleService;
import com.atguigu.util.QiniuUtil;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * TODD
 *
 * @AllClassName: com.atguigu.controller.AdminController
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private final static String PAGE_INDEX = "admin/index";
    private final static String PAGE_CREATE = "admin/create";
    private final static String PAGE_EDIT = "admin/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/admin";
    private final static String PAGE_UPLOED_SHOW = "admin/upload";

    @DubboReference
    private AdminService adminService;

    @DubboReference
    private RoleService roleService;

    @DubboReference
    private AdminRoleService adminRoleService;

    //security提供的加密对象，在WebSecurityConfig中放入到IoC容器里了，所以可以自动装配
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 处理/请求，跳转到index页，搜索处理、分页处理
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String, Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Admin> page = adminService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * 处理/create请求，跳转到添加资源页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * 处理/save请求，执行添加资源操作
     */
    @RequestMapping("/save")
    public String save(Admin admin) {
        //使用security提供的加密对象对密码进行加密
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminService.insert(admin);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/edit/id请求，跳转到修改资源页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Admin admin = adminService.getById(id);
        map.put("admin", admin);
        return PAGE_EDIT;
    }

    /**
     * 处理/update请求，执行资源修改操作
     */
    @RequestMapping(value = "/update")
    public String update(Admin admin) {
        adminService.update(admin);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/delete/id请求，执行资源删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

    /**
     * 处理/uploadShow/id请求，跳转到头像上传页面
     */
    @RequestMapping("/uploadShow/{adminId}")
    public String uploadShow(@PathVariable Long adminId, Map map) {
        map.put("adminId", adminId);
        return PAGE_UPLOED_SHOW;
    }

    /**
     * 处理/upload请求，上传图片url到数据库，上传图片到七牛云
     */
    @RequestMapping("/upload")
    public String upload(Long adminId, @RequestParam("file") MultipartFile file) throws IOException {
        //1. 将图片上传到七牛云，名称必须确保唯一！⚠️
        String fileName = UUID.randomUUID().toString() + System.currentTimeMillis();
        QiniuUtil.upload2Qiniu(file.getBytes(), fileName);
        //2. 对当前用户做修改操作，将head_url进行修改
        Admin admin = new Admin();
        admin.setId(adminId);
        //使用七牛云空间域名+图片名字拼凑图片的完整URL
        admin.setHeadUrl("http://rm5n3wdxr.hb-bkt.clouddn.com/" + fileName);
        adminService.update(admin);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/assignShow/id路径，跳转到添加角色页面
     */
    @RequestMapping("/assignShow/{adminId}")
    public String assignShow(@PathVariable Long adminId, Map map) {
        map.put("adminId", adminId);
        //需要从数据库查询得到两个List集合
        //1. 当前用户未拥有的角色信息
        //2. 当前用户已拥有的角色信息
        Map<String, List<Role>> map1 = roleService.findRoleByAdminId(adminId);
        //map1中的两个对数据，需要放在请求域(将map1中的数据添加到map内)
        map.putAll(map1);
        return "admin/assignShow";
    }

    /**
     * 为当前用户添加多个角色
     */
    @RequestMapping("/assignRole")
    //MVC的强大之处，使用数组接收字符串"1,2,3,"，可直接将请求参数转化为数组
    public String assignRole(Long adminId, Long[] roleIds) {
        System.out.println(adminId);
        System.out.println(Arrays.toString(roleIds));
        adminRoleService.insertAdminRole(adminId, roleIds);
        return PAGE_SUCCESS;
    }
}
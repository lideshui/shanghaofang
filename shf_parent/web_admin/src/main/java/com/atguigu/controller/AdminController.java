package com.atguigu.controller;

import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * TODD
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

    @Autowired
    private AdminService adminService;

    /**
     *  处理/请求，跳转到index页，搜索处理、分页处理
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
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
        map.put("admin",admin);
        return PAGE_EDIT;
    }

    /**
     * 处理/update请求，执行资源修改操作
     */
    @RequestMapping(value="/update")
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

}
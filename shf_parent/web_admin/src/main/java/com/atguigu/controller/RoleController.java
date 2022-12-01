package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoleController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 处理/role请求，搜索处理、分页处理
     */
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
     * @Description: 封装页面提交的分页参数及搜索条件
     */
    private Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    //若只有一个value，只向Map集合中放value本身
                    filters.put(paramName, values[0]);
                }
            }
        }

        //设置默认页数和分页显示数据的数量
        if(!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if(!filters.containsKey("pageSize")) {
            filters.put("pageSize", 3);
        }

        return filters;
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

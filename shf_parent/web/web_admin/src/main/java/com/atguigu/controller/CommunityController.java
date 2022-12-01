package com.atguigu.controller;

import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.CommunityController
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    private final static String LIST_ACTION = "redirect:/community";
    private final static String PAGE_INDEX = "community/index";
    private final static String PAGE_SHOW = "community/show";
    private final static String PAGE_CREATE = "community/create";
    private final static String PAGE_EDIT = "community/edit";
    private final static String PAGE_SUCCESS = "common/success";

    @DubboReference
    private CommunityService communityService;

    @DubboReference
    private DictService dictService;


    /**
     * 处理/community请求，进入index页面
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String, Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Community> page = communityService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        //将第一个选择区域的下拉框的值添加到请求域中⚠️
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList", areaList);

        return PAGE_INDEX;
    }


    /**
     * 处理/community/create请求，进入新增页面
     */
    @RequestMapping("/create")
    public String create(Map map) {
        //将第一个选择区域的下拉框的值添加到请求域中⚠️
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList", areaList);

        return PAGE_CREATE;
    }


    /**
     * 处理/community/save请求，保存新增
     */
    @RequestMapping("/save")
    public String save(Community community) {
        //使用bean作为入参，根据bean插入数据
        communityService.insert(community);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/community/edit/id请求，进入修改页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(Map map, @PathVariable Long id) {
        //根据修改的id获取信息回显
        Community community = communityService.getById(id);
        map.put("community", community);

        //将第一个选择区域的下拉框的值添加到请求域中⚠️
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList", areaList);
        return PAGE_EDIT;
    }


    /**
     * 处理/community/update请求，保存修改
     */
    @RequestMapping(value = "/update")
    public String update(Community community) {
        //使用bean作为入参，根据bean修改数据
        communityService.update(community);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/community/delete/id请求，删除实例
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        communityService.delete(id);
        return LIST_ACTION;
    }

}
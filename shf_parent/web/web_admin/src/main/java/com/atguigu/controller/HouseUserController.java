package com.atguigu.controller;

import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HouseUserController
 */
@Controller
@RequestMapping("/houseUser")
public class HouseUserController {

    @DubboReference
    private HouseUserService houseUserService;

    private final static String LIST_ACTION = "redirect:/house/show/";

    private final static String PAGE_CREATE = "houseUser/create";
    private final static String PAGE_EDIT = "houseUser/edit";
    private final static String PAGE_SUCCESS = "common/success";




    /**
     * 处理/create/id请求，进入新增页面，并传入当前房源的id
     */
    @RequestMapping("/create/{houseId}")
    public String create(Map map, @PathVariable Long houseId) {
        map.put("houseId",houseId);
        return PAGE_CREATE;
    }


    /**
     * 处理/save，保存新增
     */
    @RequestMapping("/save")
    public String save(HouseUser houseUser) {
        houseUserService.insert(houseUser);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/edit/id请求，进入编辑页面
     */
    @RequestMapping("/edit/{houseUserId}")
    public String edit(@PathVariable Long houseUserId,Map map){
        //查询到当前的
        HouseUser houseUser = houseUserService.getById(houseUserId);
        map.put("houseUser",houseUser);
        return PAGE_EDIT;
    }


    /**
     * 处理/update请求，更新实例
     */
    @RequestMapping("/update")
    public String update(HouseUser houseUser){
        houseUserService.update(houseUser);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/delete/id请求，删除实例，重定向到当前房源的详情页面
     */
    @RequestMapping("/delete/{houseId}/{houseUserId}")
    public String method(@PathVariable Long houseId,@PathVariable Long houseUserId){
        houseUserService.delete(houseUserId);
        return LIST_ACTION + houseId;
    }
}
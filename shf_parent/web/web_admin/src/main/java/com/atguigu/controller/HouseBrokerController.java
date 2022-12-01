package com.atguigu.controller;

import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.HouseBrokerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HouseBrokerController
 */
@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController {

    @DubboReference
    private HouseBrokerService houseBrokerService;


    private final static String LIST_ACTION = "redirect:/house/show/";
    private final static String PAGE_CREATE = "houseBroker/create";
    private final static String PAGE_SUCCESS = "common/success";


    /**
     * 处理/create/id请求，进入新增页面，并传入当前房源的id
     */
    @RequestMapping("/create/{houseId}")
    public String create(Map map, @PathVariable Long houseId) {
        //需要将除当前房源经纪人以外的其他经纪人添加到下拉列表
        List<Admin> adminList = houseBrokerService.findHouseOtherBroker(houseId);
        map.put("adminList",adminList);
        //传入当前房源id
        map.put("houseId",houseId);
        return PAGE_CREATE;
    }


    /**
     * 处理/save，保存新增
     */
    @RequestMapping("/save")
    public String save(HouseBroker houseBroker){
        houseBrokerService.insert(houseBroker);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/delete/id请求，删除当前房源的经纪人
     * 删除之后，应该重定向到当前房源的show页面
     */
    @RequestMapping("/delete/{houseId}/{houseBrokerId}")
    public String delete(@PathVariable Long houseId,@PathVariable Long houseBrokerId){
        //根据houseBrokerId
        houseBrokerService.delete(houseBrokerId);
        //删除完后重定向到房源的详情页面
        return LIST_ACTION + houseId;
    }


}
package com.atguigu.controller;

import com.atguigu.entity.Community;
import com.atguigu.entity.House;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseImage;
import com.atguigu.service.CommunityService;
import com.atguigu.service.HouseBrokerService;
import com.atguigu.service.HouseImageService;
import org.apache.dubbo.config.annotation.DubboReference;
import com.atguigu.result.Result;
import com.atguigu.service.HouseService;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/house")
public class HouseController {

    @DubboReference
    private HouseService houseService;

    @DubboReference
    private CommunityService communityService;

    @DubboReference
    private HouseBrokerService houseBrokerService;

    @DubboReference
    private HouseImageService houseImageService;

    public HouseController() {
    }


    /**
     * 房源列表
     */
    @PostMapping(value = "/list/{pageNum}/{pageSize}")
    public Result findListPage(@RequestBody HouseQueryVo houseQueryVo,
                               @PathVariable Integer pageNum,
                               @PathVariable Integer pageSize) {
        //调用业务层处理业务，这里的page对象会响应到前台，直接赋给前台的page对象
        //前端根据page中的数据进行列表渲染⚠️
        PageInfo<HouseVo> page = houseService.findHouseByHouseQueryVo(pageNum, pageSize, houseQueryVo);
        return Result.ok(page);
    }


    /**
     * 房源详情页面的详细信息
     */
    @RequestMapping("/info/{houseId}")
    public Result info(@PathVariable Long houseId){
        //当前房源的信息(内部关于数据字典的信息已完成转化)
        House house = houseService.getById(houseId);
        //当前房源小区的信息(内部关于数据字典的信息已完成转化)
        Community community = communityService.getById(house.getCommunityId());

        List<HouseBroker> houseBrokerList = houseBrokerService.findBrokerByHouseId(houseId);

        List<HouseImage> houseImage1List = houseImageService.findImageByHouseIdAndType(houseId, 1);

        //是否关注了该房源，目前先写死，等登陆讲完后，再去判断
        Boolean isFollow=false;

        Map<String,Object> map=new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);
        map.put("isFollow",isFollow);

        return Result.ok(map);
    }

}
package com.atguigu.controller;

import com.atguigu.entity.*;
import com.atguigu.service.*;
import org.apache.dubbo.config.annotation.DubboReference;
import com.atguigu.result.Result;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @DubboReference
    private UserFollowService userFollowService;



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
    public Result info(@PathVariable Long houseId, HttpSession session){
        //当前房源的信息(内部关于数据字典的信息已完成转化)
        House house = houseService.getById(houseId);
        //当前房源小区的信息(内部关于数据字典的信息已完成转化)
        Community community = communityService.getById(house.getCommunityId());

        List<HouseBroker> houseBrokerList = houseBrokerService.findBrokerByHouseId(houseId);

        List<HouseImage> houseImage1List = houseImageService.findImageByHouseIdAndType(houseId, 1);

        //从会话域中取出当前登录的用户信息
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
        //是否关注了该房源，默认赋值为未关注，即不登录用户显示未关注
        Boolean isFollow=false;
        //若会话域中有登录的用户信息，就发起二次查询，获取真实的关注信息
        if(userInfo!=null){
            UserFollow userFollow = userFollowService.findFollowByUserAndHouse(userInfo.getId(), houseId);
            if(userFollow!=null){
                //若关注信息的中间表对象不为空，则说明该用户关注了该房源
                isFollow=true;
            }
        }

        Map<String,Object> map=new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);
        map.put("isFollow",isFollow);

        return Result.ok(map);
    }

}
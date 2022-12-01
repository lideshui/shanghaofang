package com.atguigu.controller;

import com.atguigu.entity.*;
import com.atguigu.service.*;
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
 * @AllClassName: com.atguigu.controller.HouseController
 */
@Controller
@RequestMapping(value = "/house")
public class HouseController extends BaseController {


    @DubboReference
    private HouseService houseService;

    @DubboReference
    private DictService dictService;

    @DubboReference
    private CommunityService communityService;

    @DubboReference
    private HouseImageService houseImageService;

    @DubboReference
    private HouseBrokerService houseBrokerService;

    @DubboReference
    private HouseUserService houseUserService;

    private final static String LIST_ACTION = "redirect:/house";
    private final static String PAGE_INDEX = "house/index";
    private final static String PAGE_SHOW = "house/show";
    private final static String PAGE_CREATE = "house/create";
    private final static String PAGE_EDIT = "house/edit";
    private final static String PAGE_SUCCESS = "common/success";


    /**
     * 处理/house请求路径，跳转到index页面，展示搜索结果
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<House> page = houseService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索数据回显
        map.put("filters",filters);

        //为下拉框准备数据
        getSource(map);

        return PAGE_INDEX;
    }


    /**
     * 处理/create请求路径，进入新增页面
     */
    @RequestMapping("/create")
    public String create(Map map){
        //为下拉框准备数据
        getSource(map);
        return PAGE_CREATE;
    }


    /**
     * 处理/save请求路径，保存新增
     */
    @RequestMapping("/save")
    public String save(House house) {
        houseService.insert(house);

        return PAGE_SUCCESS;
    }


    /**
     * 处理/edit/id请求路径，到编辑修改页面
     */
    @RequestMapping("/edit/{houseId}")
    public String edit(@PathVariable Long houseId,Map map){
        //为下拉框准备数据
        getSource(map);
        House house = houseService.getById(houseId);
        map.put("house",house);
        return PAGE_EDIT;
    }



    /**
     * 处理/delete请求路径，删除数据
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        houseService.delete(id);
        return LIST_ACTION;
    }


    /**
     * 保处理/update请求路径
     */
    @RequestMapping("/update")
    public String update(House house){
        houseService.update(house);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/publish/id/status请求路径，发布房源
     */
    @RequestMapping("/publish/{id}/{status}")
    public String publish(@PathVariable Long id,@PathVariable Integer status) {
        houseService.publish(id, status);
        return LIST_ACTION;
    }


    /**
     * 封装所有的下拉选择框，多个页面都要使用
     */
    public void getSource(Map map){
        //需要所有的小区
        List<Community> communityList = communityService.findAll();
        //所有的户型
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        //所有的装修情况
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        //所有的楼层
        List<Dict> floorList = dictService.findListByDictCode("floor");
        //所有的朝向
        List<Dict> directionList = dictService.findListByDictCode("direction");
        //所有的建筑结构
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        //所有的房屋用途
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");

        map.put("communityList",communityList);
        map.put("houseTypeList",houseTypeList);
        map.put("decorationList",decorationList);
        map.put("floorList",floorList);
        map.put("directionList",directionList);
        map.put("buildStructureList",buildStructureList);
        map.put("houseUseList",houseUseList);
    }


    /**
     * 页面详情
     */
    @RequestMapping("/show/{id}")
    public String show(Map map,@PathVariable Long id) {
        //详情数据1：房源详细信息
        //ServiceImpl实现类中重写getById，有些属性只有id不满足要求，需要从字典中获取name⚠️
        House house = houseService.getById(id);
        map.put("house",house);

        //详情数据2：房源小区信息
        //ServiceImpl实现类中重写getById，有些属性只有id不满足要求，需要从字典中获取name⚠️
        Community community = communityService.getById(house.getCommunityId());
        map.put("community",community);

        //详情数据3：房源的房源图片，表：hse_house_image
        //房源和房产图片都在hse_house_image一张表上，通过type区分，1房源2房产
        //房源图片通过house_id+type1查询
        List<HouseImage> houseImage1List = houseImageService.findImageByHouseIdAndType(id,1);
        map.put("houseImage1List",houseImage1List);

        //详情数据4：房源的房产图片，表：hse_house_image
        //房产图片通过house_id+type2查询
        List<HouseImage> houseImage2List = houseImageService.findImageByHouseIdAndType(id,2);
        map.put("houseImage2List",houseImage2List);

        //详情数据5：房源的经纪人信息，表：hse_house_broker是中间表，存的房源和用户多对多关系
        List<HouseBroker> houseBrokerList = houseBrokerService.findBrokerByHouseId(id);
        map.put("houseBrokerList",houseBrokerList);

        //详情数据6：房源的房东信息，表：hse_house_user
        //一个房源可能会有多个房东，一对多的关系
        List<HouseUser> houseUserList = houseUserService.findUserByHouseId(id);
        map.put("houseUserList",houseUserList);

        return PAGE_SHOW;
    }
}
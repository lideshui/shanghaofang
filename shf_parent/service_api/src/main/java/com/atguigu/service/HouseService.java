package com.atguigu.service;

import com.atguigu.entity.House;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.HouseService
 */
public interface HouseService extends BaseService<House>{

    /**
     * 更新发布状态
     */
    void publish(Long id, Integer status);

    /**
     * 查询前台首页展示信息，HouseVo中包含了字典、小区、房源的信息，返回HouseVo对象即可
     */
    PageInfo<HouseVo> findHouseByHouseQueryVo(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo);

}


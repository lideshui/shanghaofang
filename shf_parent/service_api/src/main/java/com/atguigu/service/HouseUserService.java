package com.atguigu.service;

import com.atguigu.entity.HouseUser;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.HouseUserService
 */
public interface HouseUserService extends BaseService<HouseUser> {

    /**
     * 通过房源id查找房东信息列表
     */
    List<HouseUser> findUserByHouseId(Long houseId);

}
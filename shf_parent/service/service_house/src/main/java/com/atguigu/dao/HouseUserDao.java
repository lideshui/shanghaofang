package com.atguigu.dao;

import com.atguigu.entity.HouseUser;
import com.atguigu.service.BaseService;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.HouseUserDao
 */
public interface HouseUserDao extends BaseDao<HouseUser> {

    /**
     * 通过房源id查找房东信息列表
     */
    List<HouseUser> findUserByHouseId(Long houseId);

}
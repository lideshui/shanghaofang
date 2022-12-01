package com.atguigu.dao;

import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.HouseBrokerDao
 */
public interface HouseBrokerDao extends BaseDao<HouseBroker> {

    /**
     * 两表联查，根据房源id返回查询的房源的经纪人信息和房源信息
     */
    List<HouseBroker> findBrokerByHouseId(Long houseId);


    /**
     * 查询不是该房源经纪人的其他所有经纪人，为房源添加新的经纪人
     */
    List<Admin> findHouseOtherBroker(List<Long> ids);

}
package com.atguigu.service;

import com.atguigu.entity.House;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.HouseService
 */
public interface HouseService extends BaseService<House>{

    /**
     * 更新发布状态
     */
    void publish(Long id, Integer status);

}


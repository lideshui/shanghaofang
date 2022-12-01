package com.atguigu.service;

import com.atguigu.entity.HouseImage;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.HouseImageService
 */
public interface HouseImageService extends BaseService<HouseImage>{

    /**
     * 通过house_id+type获取房产房源图片List
     */
    List<HouseImage> findImageByHouseIdAndType(Long houseId,Integer type);

}
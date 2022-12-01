package com.atguigu.dao;

import com.atguigu.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.HouseImageDao
 */
public interface HouseImageDao extends BaseDao<HouseImage>{

    /**
     * 通过house_id+type获取房产房源图片List
     */
    List<HouseImage> findImageByHouseIdAndType(@Param("houseId") Long houseId,@Param("type") Integer type);

}
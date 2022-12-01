package com.atguigu.dao;

import com.atguigu.entity.House;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.HouseDao
 */
public interface HouseDao extends BaseDao<House>{

    /**
     * 更新发布状态
     */
    void publish(@Param("id") Long id,@Param("status") Integer status);

}
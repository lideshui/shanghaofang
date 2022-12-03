package com.atguigu.dao;

import com.atguigu.entity.UserFollow;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.UserFollowDao
 */
public interface UserFollowDao extends BaseDao<UserFollow> {

    /**
     * 获取用户是否关注该房源的信息
     */
    UserFollow findFollowByUserAndHouse(@Param("userId") Long userId, @Param("houseId") Long houseId);
}
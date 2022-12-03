package com.atguigu.service;

import com.atguigu.entity.UserFollow;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.UserFollowService
 */
public interface UserFollowService extends BaseService<UserFollow> {

    /**
     * 实现用户关注房源
     */
    void follow(Long userId,Long houseId);

    /**
     * 获取用户是否关注该房源的信息
     */
    UserFollow findFollowByUserAndHouse(Long userId, Long houseId);
}
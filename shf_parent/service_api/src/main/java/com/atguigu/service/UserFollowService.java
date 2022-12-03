package com.atguigu.service;

import com.atguigu.entity.UserFollow;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;

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

    /**
     * 查询当前登录用户的所有关注信息，UserFollowVo中包含了所有信息，最终以分页对象的形式返回
     */
    PageInfo<UserFollowVo> findUserFollow(Integer pageNum, Integer pageSize, Long userId);
}
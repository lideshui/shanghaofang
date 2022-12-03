package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserFollowDao;
import com.atguigu.entity.UserFollow;
import com.atguigu.service.UserFollowService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {

    @Autowired
    private UserFollowDao userFollowDao;


    @Override
    public BaseDao<UserFollow> getEntityDao() {
        return userFollowDao;
    }


    /**
     * 实现用户关注该房源
     */
    @Override
    public void follow(Long userId, Long houseId) {
        UserFollow userFollow=new UserFollow();
        userFollow.setHouseId(houseId);
        userFollow.setUserId(userId);
        userFollowDao.insert(userFollow);
    }

    /**
     * 查找当前用户是否关注了该房源的信息
     */
    @Override
    public UserFollow findFollowByUserAndHouse(Long userId, Long houseId) {
        return userFollowDao.findFollowByUserAndHouse(userId,houseId);
    }

}
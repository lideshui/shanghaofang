package com.atguigu.dao;

import com.atguigu.entity.UserFollow;
import com.atguigu.vo.UserFollowVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.UserFollowDao
 */
public interface UserFollowDao extends BaseDao<UserFollow> {

    /**
     * 获取用户是否关注该房源的信息
     */
    UserFollow findFollowByUserAndHouse(@Param("userId") Long userId, @Param("houseId") Long houseId);

    /**
     * 三表联查，查询当前登录用户的所有关注信息，UserFollowVo中包含了所有信息，返回该对象的List集合即可
     */
    List<UserFollowVo> findUserFollow(Long userId);
}
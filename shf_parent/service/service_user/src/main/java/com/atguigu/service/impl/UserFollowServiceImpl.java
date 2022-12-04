package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserFollowDao;
import com.atguigu.entity.UserFollow;
import com.atguigu.service.DictService;
import com.atguigu.service.UserFollowService;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DubboService
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {

    @Autowired
    private UserFollowDao userFollowDao;

    //不在同一个服务内，因此无法使用DictDao，只能从注册中心获取DictService对象来使用其方法⚠️
    @DubboReference
    private DictService dictService;

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

    /**
     * 查询当前登录用户的所有关注信息，UserFollowVo中包含了所有信息，最终以PageInfo对象的Json形式返回给前端
     */
    @Override
    public PageInfo<UserFollowVo> findUserFollow(Integer pageNum, Integer pageSize, Long userId) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserFollowVo> userFollowVoList = userFollowDao.findUserFollow(userId);
        for (UserFollowVo userFollowVo : userFollowVoList) {
            //因为粒度还是比较大，所以无法使用service_house内的DictDao的内容
            //解决方案：⚠️
            //1. 在dao层中重写DictDao（代码重复，不推荐）
            //2. 直接调用ServiceAPI，即消费DictService服务，使用其方法，使当前服务即是提供者又是消费者⚠️
            //service_user不仅仅是提供者，还是一个消费者(允许的)
            //必须配置问价内加上不需要检查服务提供者启动，不然每次必须先启动自己依赖的服务提供者！⚠️
            userFollowVo.setHouseTypeName(dictService.getNameById(userFollowVo.getHouseTypeId()));
            userFollowVo.setFloorName(dictService.getNameById(userFollowVo.getFloorId()));
            userFollowVo.setDirectionName(dictService.getNameById(userFollowVo.getDirectionId()));
        }
        return new PageInfo<>(userFollowVoList,3);
    }

}
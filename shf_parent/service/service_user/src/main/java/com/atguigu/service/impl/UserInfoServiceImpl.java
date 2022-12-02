package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserInfoDao;
import com.atguigu.entity.UserInfo;
import com.atguigu.service.UserInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public BaseDao<UserInfo> getEntityDao() {
        return userInfoDao;
    }

    /**
     * 通过手机号码获取用户的详细信息，注册操作中判断手机号是否重复
     */
    @Override
    public UserInfo findUserInfoByPhone(String phone) {
        return userInfoDao.findUserInfoByPhone(phone);
    }

}

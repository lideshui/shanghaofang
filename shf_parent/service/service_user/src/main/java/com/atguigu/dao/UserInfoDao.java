package com.atguigu.dao;

import com.atguigu.entity.UserInfo;


public interface UserInfoDao extends BaseDao<UserInfo>{

    /**
     * 通过手机号码获取用户的详细信息，注册操作中判断手机号是否重复
     */
    UserInfo findUserInfoByPhone(String phone);

}

package com.atguigu.service;

import com.atguigu.entity.UserInfo;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.UserInfoService
 */
public interface UserInfoService extends BaseService<UserInfo> {

    /**
     * 通过手机号码获取用户的详细信息，注册操作中判断手机号是否重复
     */
    UserInfo findUserInfoByPhone(String phone);
}
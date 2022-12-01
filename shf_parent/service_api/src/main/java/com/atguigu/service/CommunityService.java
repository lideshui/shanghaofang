package com.atguigu.service;

import com.atguigu.entity.Community;

import java.util.List;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.ComunityService
 */
public interface CommunityService extends BaseService<Community> {

    /**
     * 获取全部小区列表，House下拉选择框要使用
     */
    List<Community> findAll();

}
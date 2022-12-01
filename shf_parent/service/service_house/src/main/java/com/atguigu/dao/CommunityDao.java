package com.atguigu.dao;

import com.atguigu.entity.Community;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.CommunityDao
 */
public interface CommunityDao extends BaseDao<Community>{

    /**
     * 获取全部小区列表，House下拉选择框要使用
     */
    List<Community> findAll();
}
package com.atguigu.dao;

import com.atguigu.entity.Dict;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.DictDao
 */
public interface DictDao {
    /**
     * 通过parent_id获取所有子节点
     */
    List findListByParentId(Long id);

    /**
     * 通过parent_id判断是否为父节点
     */
    Integer isParentNode(Long id);

}
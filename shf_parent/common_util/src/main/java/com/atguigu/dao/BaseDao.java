package com.atguigu.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.BaseDao
 */
public interface BaseDao<T> {

    /**
     * 获取index页面的列表实体数据，以及查询结果的分页展示
     */
    List<T> findPage(Map<String,Object> filters);


    /**
     * 插入一个实体
     */
    void insert(T t);


    /**
     * 根据ID删除实体，参数ID可以是字符串型也可以是整型
     */
    void delete(Serializable id);


    /**
     * 更新一个实体
     */
    void update(T t);


    /**
     * 根据ID获取实体，参数ID可以是字符串型也可以是整型
     */
    T getById(Serializable id);

}
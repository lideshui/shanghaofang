package com.atguigu.service;

import com.github.pagehelper.PageInfo;
import java.io.Serializable;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.BaseService
 */
public interface BaseService<T> {

    /**
     * 获取实体数据，包装到PageInfo分页对象中返回
     */
    PageInfo<T> findPage(Map<String,Object> filters);


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
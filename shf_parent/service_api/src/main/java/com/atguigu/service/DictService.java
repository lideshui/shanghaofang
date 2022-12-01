package com.atguigu.service;

import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.DictService
 */
public interface DictService {


    /**
     * 通过id获取所有子节点
     */
    List<Map<String,Object>> getNodesByParentId(Long id);


}
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

    /**
     * 为Community下拉框服务：根据编码获取子节点数据列表
     */
    List<Dict> findListByDictCode(String dictCode);


    /**
     * 为Community下拉框服务：根据上级id获取子节点数据列表
     */
    List<Dict> findListByParentId(Long parentId);

    /**
     * 为service_user服务：根据id获取其在字典中对应的name
     */
    String getNameById(Long id);

}
package com.atguigu.service.impl;

import com.atguigu.dao.DictDao;
import com.atguigu.entity.Dict;
import com.atguigu.service.DictService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.DictServiceImpl
 */

@DubboService
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    /**
     * 通过id获取所有子节点
     */
    @Override
    public List<Map<String, Object>> getNodesByParentId(Long id) {

        //获取所有子节点
        List<Dict> list = dictDao.findListByParentId(id);

        //创建处理后的list集合
        List<Map<String, Object>> result = new ArrayList<>();

        //遍历子节点，转换为合适的样式[{ id:'',name:'',isParent:true}...]
        for (Dict dict : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",dict.getId());
            map.put("name",dict.getName());

            //调用dao层查询本次循环的节点是否为父节点
            map.put("isParent",dictDao.isParentNode(dict.getId()) > 0 ? true : false);

            //存储每次转换的结果
            result.add(map);
        }

        return result;
    }

    /**
     * 为下拉框服务：根据编码间接获取子节点列表
     */
    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        //先根据编码获取对应的实例
        Dict dict = dictDao.getByDictCode(dictCode);
        if(null == dict) return null;
        //如果实例不为空，就获取当前实例的所有子节点
        return this.findListByParentId(dict.getId());
    }

    /**
     * 为下拉框服务：根据上级id获取子节点列表
     */
    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return dictDao.findListByParentId(parentId);
    }

    /**
     * 为service_user提供服务：根据id获取其在字典中对应的name
     */
    @Override
    public String getNameById(Long id){
        return dictDao.getNameById(id);
    }

}
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

}
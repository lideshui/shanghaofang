package com.atguigu.controller;

import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.DictController
 */
@Controller
@RequestMapping("/dict")
public class DictController {
    private final static String PAGE_INDEX = "dict/index";

    @DubboReference
    private DictService dictService;

    /**
     * 访问/dict路径时返回dict/index页面
     */
    @RequestMapping
    public String index() {
        return PAGE_INDEX;
    }

    /**
     * 通过异步获取子节点渲染到页面上
     * 返回的结果是封装后的异步请求统一返回值模版Result
     * 第一次进入页面时，id值为空，通过defaultValue给参数默认值，这一步很关键！⚠️
     */
    @RequestMapping("/findZnodes")
    @ResponseBody
    public Result findByParentId(@RequestParam(value = "id", defaultValue = "0") Long id) {
        List<Map<String, Object>> result = dictService.getNodesByParentId(id);
        return Result.ok(result);
    }

    /**
     * 为下拉列表服务，根据编码间接获取子节点列表，第一次选择
     */
    @RequestMapping(value = "findListByDictCode/{dictCode}")
    @ResponseBody
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findListByDictCode(dictCode);
        return Result.ok(list);
    }

    /**
     * 为下拉列表服务，根据上级id获取子节点列表，第二次选择
     */
    @RequestMapping(value = "findListByParentId/{parentId}")
    @ResponseBody
    public Result<List<Dict>> findListByParentId(@PathVariable Long parentId) {
        List<Dict> list = dictService.findListByParentId(parentId);
        return Result.ok(list);
    }
}
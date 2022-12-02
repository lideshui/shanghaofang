package com.atguigu.controller;

import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.DictController
 */
@Controller
@RequestMapping("/dict")
@ResponseBody
public class DictController {
    private final static String PAGE_INDEX = "dict/index";

    @DubboReference
    private DictService dictService;


    /**
     * 请求路径：/dict/findListByDictCode/code，作用：根据code返回字典内相关信息
     */
    @RequestMapping("/findListByDictCode/{code}")
    public Result findListByDictCode(@PathVariable String code){
        List<Dict> dictList = dictService.findListByDictCode(code);
        return Result.ok(dictList);
    }


    /**
     * 请求路径：findListByParentId/parentId，作用：根据id二次查找下级地区板块
     */
    @RequestMapping("/findListByParentId/{parentId}")
    public Result findListByParentId(@PathVariable Long parentId){
        List<Dict> dictList = dictService.findListByParentId(parentId);
        return Result.ok(dictList);
    }

}
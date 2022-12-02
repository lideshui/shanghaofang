package com.atguigu.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import com.atguigu.result.Result;
import com.atguigu.service.HouseService;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/house")
public class HouseController {

    @DubboReference
    private HouseService houseService;

    /**
     * 房源列表
     */
    @PostMapping(value = "/list/{pageNum}/{pageSize}")
    public Result findListPage(@RequestBody HouseQueryVo houseQueryVo,
                               @PathVariable Integer pageNum,
                               @PathVariable Integer pageSize) {
        //调用业务层处理业务，这里的page对象会响应到前台，直接赋给前台的page对象
        //前端根据page中的数据进行列表渲染⚠️
        PageInfo<HouseVo> page = houseService.findHouseByHouseQueryVo(pageNum, pageSize, houseQueryVo);
        return Result.ok(page);
    }

}
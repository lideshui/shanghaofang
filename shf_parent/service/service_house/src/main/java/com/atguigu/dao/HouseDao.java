package com.atguigu.dao;

import com.atguigu.entity.House;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.HouseDao
 */
public interface HouseDao extends BaseDao<House>{

    /**
     * 更新发布状态
     */
    void publish(@Param("id") Long id,@Param("status") Integer status);

    /**
     * 查询前台首页展示信息，HouseVo中包含了字典、小区、房源的信息，返回HouseVo对象即可
     */
    List<HouseVo> findHouseByQueryVo(HouseQueryVo houseQueryVo);
}
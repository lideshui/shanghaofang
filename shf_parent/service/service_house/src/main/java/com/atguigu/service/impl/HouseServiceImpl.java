package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.DictDao;
import com.atguigu.dao.HouseDao;
import com.atguigu.entity.House;
import com.atguigu.service.HouseService;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HouseServiceImpl
 */
@DubboService
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseDao houseDao;

    @Autowired
    private DictDao dictDao;

    @Override
    public BaseDao<House> getEntityDao() {
        return houseDao;
    }


    /**
     * 更新发布状态
     */
    @Override
    public void publish(Long id, Integer status) {
        houseDao.publish(id,status);
    }


    /**
     * 重写getById，通过字典和id，为其房源详细信息赋值
     */
    @Override
    public House getById(Serializable id) {
        House house = houseDao.getById(id);

        //需要通过数据字典中数据的id值获取对应的name值
        //为户型name赋值
        house.setHouseTypeName(dictDao.getNameById(house.getHouseTypeId()));

        //为楼层name赋值
        house.setFloorName(dictDao.getNameById(house.getFloorId()));

        //为建筑结构name赋值
        house.setBuildStructureName(dictDao.getNameById(house.getBuildStructureId()));

        //为朝向name赋值
        house.setDirectionName(dictDao.getNameById(house.getDirectionId()));

        //为装修情况name赋值
        house.setDecorationName(dictDao.getNameById(house.getDecorationId()));

        //房屋用途name赋值
        house.setHouseUseName(dictDao.getNameById(house.getHouseUseId()));
        return house;
    }

    /**
     * 查询前台首页展示信息，HouseVo中包含了字典、小区、房源的信息，返回HouseVo对象即可
     * 关于房源中个别属性只有id，可再去字典中根据id查到name再赋值即可
     */
    @Override
    public PageInfo<HouseVo> findHouseByHouseQueryVo(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo) {
        PageHelper.startPage(pageNum,pageSize);
        List<HouseVo> houseVoList = houseDao.findHouseByQueryVo(houseQueryVo);
        for (HouseVo houseVo : houseVoList) {
            //需要将数据字典中三个id值换成三个name值: 需要调用DictDao
            houseVo.setHouseTypeName(dictDao.getNameById(houseVo.getHouseTypeId()));
            houseVo.setDirectionName(dictDao.getNameById(houseVo.getDirectionId()));
            houseVo.setFloorName(dictDao.getNameById(houseVo.getFloorId()));
        }
        return new PageInfo<>(houseVoList,3);
    }
}


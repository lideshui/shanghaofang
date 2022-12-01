package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.HouseImageDao;
import com.atguigu.entity.HouseImage;
import com.atguigu.service.HouseImageService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HouseImageServiceImpl
 */
@DubboService
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {

    @Autowired
    private HouseImageDao houseImageDao;

    @Override
    public BaseDao<HouseImage> getEntityDao() {
        return houseImageDao;
    }

    /**
     * 通过house_id+type获取房产房源图片List
     */
    @Override
    public List<HouseImage> findImageByHouseIdAndType(Long houseId, Integer type) {
        return houseImageDao.findImageByHouseIdAndType(houseId,type);
    }

}
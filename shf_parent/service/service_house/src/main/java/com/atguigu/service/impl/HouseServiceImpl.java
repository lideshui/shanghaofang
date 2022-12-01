package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.HouseDao;
import com.atguigu.entity.House;
import com.atguigu.service.HouseService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HouseServiceImpl
 */
@DubboService
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseDao houseDao;

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
}


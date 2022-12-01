package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.HouseUserDao;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HouseUserServiceImpl
 */
@DubboService
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {

    @Autowired
    private HouseUserDao houseUserDao;

    @Override
    public BaseDao<HouseUser> getEntityDao() {
        return houseUserDao;
    }

    /**
     * 通过房源id查找房东信息列表
     */
    @Override
    public List<HouseUser> findUserByHouseId(Long houseId) {
        return houseUserDao.findUserByHouseId(houseId);
    }
}
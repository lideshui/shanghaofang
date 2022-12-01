package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.HouseBrokerDao;
import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.HouseBrokerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HouseBrokerServiceImpl
 */
@DubboService
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker> implements HouseBrokerService {

    @Autowired
    private HouseBrokerDao houseBrokerDao;

    @Override
    public BaseDao<HouseBroker> getEntityDao() {
        return houseBrokerDao;
    }


    /**
     * 两表联查，根据房源id返回查询的房源的经纪人信息和房源信息
     */
    @Override
    public List<HouseBroker> findBrokerByHouseId(Long houseId) {
        return houseBrokerDao.findBrokerByHouseId(houseId);
    }


    /**
     * 查询房源经纪人以外的其他所有经纪人，为房源添加新的经纪人
     */
    @Override
    public List<Admin> findHouseOtherBroker(Long houseId) {

        //第一步：查询到当前房源的所有经纪人信息
        List<HouseBroker> brokerList = houseBrokerDao.findBrokerByHouseId(houseId);

        //通过循环取出经纪人的id，放到ids集合中
        List<Long> ids=new ArrayList<>();
        for (HouseBroker houseBroker : brokerList) {
            ids.add(houseBroker.getBrokerId());
        }

        //第二步：查询admin表格，将上面的经纪人排除掉(暂时写在houseBrokerDao)
        //对admin表格的查询，按理说应该调用AdminDao,但是AdminDao在acl内，无法调用，到微服务架构解决
        //注意：service和dao会分开，就可以调用，SOA架构，粒度比较大，容易出现重复问题⚠️
        return houseBrokerDao.findHouseOtherBroker(ids);
    }
}
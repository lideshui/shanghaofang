package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.CommunityDao;
import com.atguigu.dao.DictDao;
import com.atguigu.entity.Community;
import com.atguigu.service.CommunityService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.CommunityServiceImpl
 */
@DubboService
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {

    @Autowired
    private CommunityDao communityDao;

    @Autowired
    private DictDao dictDao;

    @Override
    public BaseDao<Community> getEntityDao() {
        return communityDao;
    }

    /**
     * 重写封装后的PageInfo方法，因为需要为区域和板块查询赋值
     * 虽然我们不知道区域和板块的名字，但是有他们的id，可以根据id去字典里查⚠️
     * 循环的每一个对象，都将查到的结果name，设置到自己areaName和plateName属性上
     * 使用两表联查也可以，但是复杂度相对较高
     */
    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        //当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);
        PageHelper.startPage(pageNum, pageSize);

        List<Community> list = communityDao.findPage(filters);
        for(Community community : list) {
            //获取区域和板块的名字
            String areaName = dictDao.getNameById(community.getAreaId());
            String plateName = dictDao.getNameById(community.getPlateId());
            //设置区域和板块的名字
            community.setAreaName(areaName);
            community.setPlateName(plateName);
        }
        return new PageInfo<Community>(list, 3);
    }
}
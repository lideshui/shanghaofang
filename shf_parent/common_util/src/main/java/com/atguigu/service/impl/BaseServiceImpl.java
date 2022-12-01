package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.service.BaseService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODD
 * @AllClassName: com.atguigu.service.impl.BaseServiceImpl
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {


    //模版方法设计模式，通过该抽象方法获取实际的dao层对象
    //service层实现类重写该方法返回dao层对象，就可以用该方法返回值调用其他方法⚠️
    public abstract BaseDao<T> getEntityDao();

    /**
     * 搜索结果分页显示
     */
    @Override
    public PageInfo<T> findPage(Map<String, Object> filters) {
        //当前页数，common-util引入工具类：CastUtil
        //先获取请求参数处理方法getFilters中设置的初始化值，未设置时使用默认值⚠️
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);

        //分页插件，必须设置在查询之前⚠️
        PageHelper.startPage(pageNum, pageSize);
        //使用方法返回的dao层对象调用
        List<T> list = getEntityDao().findPage(filters);
        //构造方法中传入list集合和每页显示条数
        //通过pageInfo.getList()可获取list集合的数据，将pageInfo对象放到请求域就可以共享到前端
        return new PageInfo<T>(list, 3);
    }


    /**
     * 新增一个实体
     */
    @Override
    public void insert(T t) {
        getEntityDao().insert(t);
    }


    /**
     * 根据ID获取实体，参数ID可以是字符串型也可以是整型
     */
    @Override
    public T getById(Serializable id) {
        return getEntityDao().getById(id);
    }


    /**
     * 更新一个实体
     */
    @Override
    public void update(T t) {
        getEntityDao().update(t);
    }


    /**
     * 根据ID删除实体，参数ID可以是字符串型也可以是整型
     */
    @Override
    public void delete(Serializable id) {
        getEntityDao().delete(id);
    }

}
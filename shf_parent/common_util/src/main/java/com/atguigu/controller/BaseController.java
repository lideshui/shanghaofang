package com.atguigu.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.BaseController
 */
public class BaseController {

    /**
     * 封装页面提交的分页参数及搜索条件
     * @param request
     * @return
     */
    protected Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    //若只有一个value，只向Map集合中放value本身
                    filters.put(paramName, values[0]);
                }
            }
        }
        //判断请求参数内没有pageNum和pageSize的话，初始值设置为1和3
        if(!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if(!filters.containsKey("pageSize")) {
            filters.put("pageSize", 3);
        }

        return filters;
    }
}
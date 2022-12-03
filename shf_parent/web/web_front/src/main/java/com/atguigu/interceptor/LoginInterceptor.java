package com.atguigu.interceptor;

import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.util.WebUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.interceptor.LoginInterceptor
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 重写拦截器preHandle方法，拦截对应的URL后通过判断会话域中有无登录的用户信息决定是否放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证是否处于登录状态
        HttpSession session = request.getSession();
        //从请求域中获取当前登录用户
        Object userInfo = session.getAttribute("userInfo");
        //如果没获取到，说明为空，需要跳转到登录页面
        if(userInfo==null){
            //这里处理到是异步请求不是Servlet，不可以用重定向或转发直接跳转⚠️
            //解决方案：借助工具类WebUtil返回Json对象，并响应给前端208用户未登录状态码
            //注意：还需要在spring-mvc.xml还需要配置拦截器的拦截路径
            WebUtil.writeJSON(response, Result.build(null, ResultCodeEnum.LOGIN_AUTH));
            return false;
        }
        return true;
    }
}
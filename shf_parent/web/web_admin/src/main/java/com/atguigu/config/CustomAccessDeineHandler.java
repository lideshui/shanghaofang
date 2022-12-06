package com.atguigu.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.config.CustomAccessDeineHandler
 */
public class CustomAccessDeineHandler implements AccessDeniedHandler {
    /**
     * 权限验证不通过的处理器，未通过授权统一跳转到/auth路径
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.sendRedirect("/auth");
    }
}
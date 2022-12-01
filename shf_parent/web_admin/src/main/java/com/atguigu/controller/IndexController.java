package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.IndexController
 */
@Controller
public class IndexController {

    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";

    /**
     * 首页默认访问路径
     */
    @RequestMapping("/")
    public String index() {
        return PAGE_INDEX;
    }

    /**
     * 首页iframe窗体中内置的欢迎页面
     */
    @RequestMapping("/main")
    public String main() {
        return PAGE_MAIN;
    }

}

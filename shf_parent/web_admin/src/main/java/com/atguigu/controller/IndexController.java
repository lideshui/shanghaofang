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

    @RequestMapping("/")
    public String index() {
        return PAGE_INDEX;
    }

}

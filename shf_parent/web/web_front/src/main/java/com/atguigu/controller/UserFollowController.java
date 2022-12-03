package com.atguigu.controller;

import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.service.UserFollowService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.UserFollowController
 */
@Controller
@RequestMapping("/userFollow")
@ResponseBody
public class UserFollowController {

    @DubboReference
    private UserFollowService userFollowService;

    /**
     * 处理/auth/follow/id路径，用户关注当前房源的请求
     */
    @RequestMapping("/auth/follow/{houseId}")
    public Result follow(@PathVariable Long houseId, HttpSession session){
        //目前先保持手动登录，确保请求域中有用户信息，一会再去实现拦截器
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        userFollowService.follow(userInfo.getId(),houseId);
        return Result.ok();
    }
}
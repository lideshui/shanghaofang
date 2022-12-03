package com.atguigu.controller;

import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.service.UserFollowService;
import com.atguigu.vo.UserFollowVo;
import com.github.pagehelper.PageInfo;
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

    /**
     * 处理/auth/list/pageNum/pageSize路径，获取用户所有关注的房源
     * 先从会话域中获取当前用户的登录信息，再以分页对象的形式返还给前端
     */
    @RequestMapping("/auth/list/{pageNum}/{pageSize}")
    public Result list(@PathVariable Integer pageNum,@PathVariable Integer pageSize,HttpSession session){
        //查询当前登录人关注的房源信息(带分页)
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        PageInfo<UserFollowVo> userFollowVoList = userFollowService.findUserFollow(pageNum, pageSize, userInfo.getId());
        return Result.ok(userFollowVoList);
    }

    /**
     * 处理/auth/cancelFollow/id路径，取消关注房源
     */
    @RequestMapping("/auth/cancelFollow/{userFollowId}")
    public Result cancelFollow(@PathVariable Long userFollowId){
        userFollowService.delete(userFollowId);
        return Result.ok();
    }
}
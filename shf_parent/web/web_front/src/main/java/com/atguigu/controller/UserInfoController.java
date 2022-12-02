package com.atguigu.controller;

import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.MD5;
import com.atguigu.vo.RegisterVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.UserInfoController
 */
@Controller
@RequestMapping("/userInfo")
@ResponseBody
public class UserInfoController {

    @DubboReference
    private UserInfoService userInfoService;

    /**
     * 处理/sendCode/phone路径，用户输入手机号后获取验证码的请求
     * 此处模拟8888验证码返回给用户， 正常情况下应该以短信的形式，并且将code放在会话域
     */
    @RequestMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone, HttpSession session){
        //真实环境就是一个4位或者6位的随机数
        //如果是真实环境，需要将code发送到用户的手机上，并且将code放在会话域(后续验证验证码是否正确)
        String code="8888";
        //现在是模拟，将code响应给前台，还是将验证码放在会话域
        session.setAttribute("code",code);
        return Result.ok(code);
    }


    /**
     * 处理/register路径，用户注册操作
     * registerVo请求参数包裹着注册所需的全部信息
     */
    @RequestMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpSession session){
        //1. 获取到注册的数据(code/手机号/密码/昵称)
        String code = registerVo.getCode();
        String phone = registerVo.getPhone();
        String password = registerVo.getPassword();
        String nickName = registerVo.getNickName();
        //2. 校验参数是否为空
        if(StringUtils.isEmpty(code)||StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)||StringUtils.isEmpty(nickName)){
            //若有空参数，直接给前台一个参数错误203响应
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }
        //3. 校验验证码是否正确
        Object trueCode = session.getAttribute("code");
        if(!trueCode.equals(code)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }
        //4. 校验手机号是否重复(根据phone，去数据库做二次查询)
        UserInfo userInfo = userInfoService.findUserInfoByPhone(phone);
        if(userInfo!=null){
            //若不为空，则查询到了实例对象，说明手机号已被使用
            return Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
        }

        //5. 将数据保存到数据库即可
        UserInfo info=new UserInfo();
        info.setPhone(phone);
        info.setNickName(nickName);
        //使用MD5对密码进行加密
        info.setPassword(MD5.encrypt(password));
        //status不设置也行，因为默认值为1。1表示该用户锁定，0表示该用户正常
        info.setStatus(1);

        //新增操作
        userInfoService.insert(info);

        return Result.ok();
    }

}
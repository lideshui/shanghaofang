package com.atguigu.controller;

import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.MD5;
import com.atguigu.util.RedisUtil;
import com.atguigu.util.SendSms;
import com.atguigu.vo.LoginVo;
import com.atguigu.vo.RegisterVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
     * 处理/sendSms/phone路径，调用阿里云短信服务器发送短信
     */
    @RequestMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone, HttpSession session) {
        //先判断redis内有验证码，有的话就不发送了
        Map redisInfo = RedisUtil.findCode(phone);
        Long time = (Long) redisInfo.get("timeout");
        if (time != -2) {
            return Result.ok(time);
        } else {
            //生成验证码并放到redis中
            Integer hashCode = UUID.randomUUID().toString().hashCode();
            // String.hashCode()可能会是负数，如果为负数需要转换为正数
            hashCode = hashCode < 0 ? -hashCode : hashCode;
            //截取六位数验证码
            String code = hashCode.toString().substring(0, 6);
            //设置超时时间，单位(秒)
            Integer timeout = 60;
            //向用户发送验证码
            Boolean sendOk = SendSms.sendSms(phone, code);
            if (sendOk) {
                //发送成功，在redis中存储验证码
                RedisUtil.addCode(phone, code, timeout);
            } else {
                System.out.println("发送失败");
            }
        }
        return Result.ok(false);
    }


    /**
     * 处理/register路径，用户注册操作
     * registerVo请求参数包裹着注册所需的全部信息
     */
    @RequestMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpSession session) {
        //1. 获取到注册的数据(code/手机号/密码/昵称)
        String code = registerVo.getCode();
        String phone = registerVo.getPhone();
        String password = registerVo.getPassword();
        String nickName = registerVo.getNickName();
        //2. 校验参数是否为空
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(nickName)) {
            //若有空参数，直接给前台一个参数错误203响应
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }
        //3. 校验验证码是否正确
        //先根据手机号获取验证码，然后再判断
        Map redisInfo = RedisUtil.findCode(phone);
        String trueCode = (String) redisInfo.get("code");
        if (!code.equals(trueCode)) {
            return Result.build(null, ResultCodeEnum.CODE_ERROR);
        }
        //4. 校验手机号是否重复(根据phone，去数据库做二次查询)
        UserInfo userInfo = userInfoService.findUserInfoByPhone(phone);
        if (userInfo != null) {
            //若不为空，则查询到了实例对象，说明手机号已被使用
            return Result.build(null, ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        //5. 将数据保存到数据库即可
        UserInfo info = new UserInfo();
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


    /**
     * 处理/login路径，用户登录操作
     * 先校验，通过校验后再将信息放到会话域一份，并返回给前端一份
     */
    @RequestMapping("/login")
    public Result login(@RequestBody LoginVo loginVo, HttpSession session) {
        //1. 获取前端请求参数，手机号和密码
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();//password是明文
        //2. 前端请求参数的非空校验
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }
        //3. 验证用户名是否正确(根据phone去查询UserInfo对象)
        UserInfo userInfo = userInfoService.findUserInfoByPhone(phone);
        if (userInfo == null) {
            return Result.build(null, ResultCodeEnum.ACCOUNT_ERROR);
        }
        //4. 验证登录密码是否正确，注册时使用MD5加密，相同的明文多次加密的结果是一样的
        //还有其他加密方式，这里必须根据注册时的加密方式来编写对应的校验方式
        if (!userInfo.getPassword().equals(MD5.encrypt(password))) {
            //返回密码不正确的相关验证码和信息
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }
        //5. 验证用户是否被锁定(status是否是1)
        if (userInfo.getStatus() == 0) {
            //返回用户锁定的相关验证码和信息
            return Result.build(null, ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        //6. 将当前登录人信息保存在会话域
        session.setAttribute("userInfo", userInfo);

        //7. 将用户信息(手机号、昵称)响应给前端
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("nickName", userInfo.getNickName());

        return Result.ok(map);//这里返回200的响应
    }


    /**
     * 处理/logout路径，用户登出请求
     */
    @RequestMapping("/logout")
    public Result logout(HttpSession session) {
        //从会话域中删除用户信息
        session.removeAttribute("userInfo");
        return Result.ok();
    }

}
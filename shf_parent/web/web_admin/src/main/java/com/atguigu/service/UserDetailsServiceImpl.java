package com.atguigu.service;

import com.atguigu.entity.Admin;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.UserDetailsServiceImpl
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @DubboReference
    private AdminService adminService;

    /**
     * SpringSecurity在验证用户名和密码的时，默认调用UserDetailsService的loadUserByUsername方法
     * 我们选择重写，调用的就是重写之后的
     * username就是在登录页面输入的用户名
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("用户输入的用户名：" + username);
        //1. 通过用户名去数据查询Admin对象回来
        Admin admin = adminService.findAdminByUsername(username);
        //2. 如果对象有值，说明用户名是对的，然后在让SpringSecurity去完成密码的校验(有加密)
        if (admin == null) {
            //如果没有查到对应的实例，说明用户名不存在
            throw new UsernameNotFoundException("用户名不存在！");
        }
        //若用户存在，则开始校验密码。admin.getPassword()就是从数据库查询回来的密码--> 必须是加密的密码
        //无论用户实例是什么对象，都会根据对象的参数返回SpringSecurity提供的User对象，即当前登录对象的加工后的认证对象
        return new User(username, admin.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));
    }
}
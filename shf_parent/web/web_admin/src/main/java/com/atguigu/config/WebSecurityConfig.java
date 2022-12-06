package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//标记该类是配置类
@Configuration
//@EnableWebSecurity是开启SpringSecurity的默认行为
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置加密方式，必须指定加密方式，上下加密方式要一致
     * Bean注解是将这个方法的返回值对象装配到IoC容器，为了注册的时候可以从IoC容器内获取到这个对象进行加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 设置允许iframe嵌套显示，默认Spring Security不允许iframe嵌套显示
     * 可自己配置SpringSecurity，配置登出、登入等设置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //不再需要父级的方法，自己配置SpringSecurity
        //super.configure(http);

        //设置允许iframe进行嵌套
        http.headers().frameOptions().disable();

        //and()方法返回的是http对象本身⚠️
        //设置不需要认证就可以访问的路径(所有的静态资源、/login)
        http.authorizeRequests()
                // 允许匿名访问的路径
                .antMatchers("/static/**","/login").permitAll()
                //其他的请求都需要认证
                .anyRequest().authenticated();

        //设置登录
        http.formLogin()
                //设置自己的登录页面，不再使用SpringSecurity默认提供的
                //未登录时，访问哪个路径都跳转到这里
                .loginPage("/login")
                //登录认证成功后默认转跳的路径
                .defaultSuccessUrl("/");

        //设置退出
        http.logout()
                //退出登陆的路径，指定spring security拦截的注销url
                //退出功能是security提供的
                .logoutUrl("/logout")
                //用户退出后要被重定向的url
                .logoutSuccessUrl("/login");

        //跨域设置
        http.csrf()
                //需要将跨域请求关闭，不然请求不到/login无法退出
                .disable();
    }

}



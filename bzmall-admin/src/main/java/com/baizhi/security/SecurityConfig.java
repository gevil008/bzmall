package com.baizhi.security;

import com.baizhi.service.impl.BzAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @EnableGlobalMethodSecurity(prePostEnabled = true) 配置开启注解式授权
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BzAdminServiceImpl adminService;
    /**
     * Authentication 认证
     * 在shiro和SpringSecurity中  所有以Authen开头的单词都和认证业务有关
     *
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 制造假的认证用户数数据
         *
         * inMemoryAuthentication 在内存中制造假数据
         * withUser 定义账号
         * password 定义密码 但是SpringSecurity默认加密
         * 如果不想使用加密 需要给密码添加前缀{noop}该前缀是一种声明用于让SpringSEcurity知道密码的加密方式
         * 不参与密码校验
         * roles 是角色 和授权有关系
         */
        /*
        auth.inMemoryAuthentication()
                .withUser("zhangsan")
                .password("$2a$10$95nmnQWbio4EgUSSriwjIOzaIUR.nSbq/Gg/YTvL2hXACUnLr.6Qy")
                // .roles("ADMIN")
                *//**
                 * 声明zhangsan的权限信息
                 * authorities 定义角色和权限信息
                 * 定义角色的时候 角色名前面需要添加ROLE_
                 * 没有前缀的的字符串就是权限
                 *//*
                .authorities("ROLE_admin","admin:add","product:select","menu:select")

                .and()
                .withUser("lisi")
                .password("$2a$10$95nmnQWbio4EgUSSriwjIOzaIUR.nSbq/Gg/YTvL2hXACUnLr.6Qy")
                .authorities("ROLE_superadmin","admin:delete");
                // .roles("ADMIN");
        */
        /**
         * 通过业务类查询数据库
         * 校验验证码
         */
        auth.userDetailsService(adminService)
            .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * HttpSecurity 配置拦截规则 跳转 和 自定义登录页面
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * authorizition 授权
         * 在shiro和SpringSecurity中 所有Author开头的单词都和授权业务有关系
         *
         * authorizeRequests 配置拦截规则
         * antMatchers 配置路径
         * permitAll 不拦截
         */
        http.authorizeRequests()
//                配置不拦截
                .antMatchers(
                        "/admin/**",
                        "/img/**",
                        "/css/**",
                        "/js/**",
                        "/ztree/**",
                        "/login.jsp",
                        "/login",
                        "/layui/**")
                .permitAll()
//                拦截所有 配置一般不会使用/** 而是独立配置
//                anyRequest 代表所有路径
                /**
                 * .antMatchers("/menu/getZTreeNodes")
                 *                 .hasAuthority("menu:select")
                 *                 具有menu:select权限的用户才能访问此接口
                 */
                /*.antMatchers("/menu/getZTreeNodes")
                .hasAuthority("menu:select")

                *//**
                 * .antMatchers("/brand/brands").hasRole("superadmin")
                 * 具有superadmin权限的用户才能访问接口
                 *//*
                .antMatchers("/brand/brands").hasRole("superadmin")*/
                .anyRequest()
                .authenticated();
        /**
         * 自定义登录页面
         *
         * formLogin() 代表表单登录
         * loginPage 自定义登录页面
         * loginProcessingUrl 定义登录方法的地址 /login就是SpringSecurity中的认证方法
         * successForwardUrl 登录成功后的地址
         * failureForwardUrl 登录失败后的地址
         */
        http.formLogin()
                .loginPage("/login.jsp")
                .successForwardUrl("/admin/successForward")
                .failureForwardUrl("/admin/failureForward")
                .loginProcessingUrl("/login")
                .and()
                .csrf()
                .disable()
        ;

//       html iframe标签引用二级页面 会被默认拦截
//        可以配置不拦截
        http.headers().frameOptions().disable();
    }

}

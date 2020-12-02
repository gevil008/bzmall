package com.baizhi.service.impl;

import com.baizhi.dao.BzAdminMapper;
import com.baizhi.entity.BzAdmin;
import com.baizhi.log.LogAnnotation;
import com.baizhi.service.BzAdminService;
import com.baizhi.service.BzRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j  // 注解方式
public class BzAdminServiceImpl extends ServiceImpl<BzAdminMapper, BzAdmin>
        implements BzAdminService, UserDetailsService {
    @Autowired
    private BzAdminMapper adminDao;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BzRoleService bzRoleService;
    /**
     * 创建日志打印对象
     *
     * Logger LoggerFactory 都是 org.slf4j包
     * 参数 ：当前的类的类对象
     */
    // private Logger logger = LoggerFactory.getLogger(BzAdminServiceImpl.class);
    @Override
    @LogAnnotation(content = "查询管理员列表信息")
    public List<BzAdmin> selectAllAdmin() {
        /**
         * 使用日志对象打印日志信息 取代打印语句
         *
         * 一般代码的调试 取代打印语句 使用debug 或者 info
         * 警告信息warn 例如：权限操作
         * 异常处理中使用 error
         */
        /*logger.debug("这是debug等级菜单日志");
        logger.info("这是info等级菜单日志");
        logger.warn("这是warn等级菜单日志");
        logger.error("这是error等级菜单日志");*/

        /**
         * 注解方式
         */
        // log.debug("这是使用注解debug等级菜单日志");
        // log.info("这是使用注解info等级菜单日志");

        return adminDao.selectList(null);
    }

    @Override
    public boolean addBzAdmin(BzAdmin bzAdmin) {
        String encode = bCryptPasswordEncoder.encode(bzAdmin.getPassword());
        bzAdmin.setPassword(encode);
        return this.save(bzAdmin);
    }

    /**
     * 该方法就是SpringSecurity预留的查询数据库的方法 许我手动实现
     *
     * 形参是 用户名(用户在浏览器上输入的用户名)
     */
    @Override
    public UserDetails loadUserByUsername(String username){
        /**
         * 验证码校验
         */
        String code = (String) request.getSession().getAttribute("code");
        String code1 = request.getParameter("code");
        System.err.println(code1);
        if (!code.equals(code1)){
            throw new UsernameNotFoundException("验证码错误");
        }

        BzAdmin admin = getOne(new QueryWrapper<BzAdmin>().eq("username", username));
        if (admin == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        /**
         * 使用官方的查询授权信息
         */
/*
        Set<String> roles = bzRoleService.getRolesByUsername(username);
        System.err.println(roles);

        // 角色前加前缀
        roles = roles.stream()
                .map(roleName -> {
                    return "ROLE_" + roleName;
        }).collect(Collectors.toSet());

        Set<String> permissions = bzRoleService.getPermissionsByUsername(username);
        permissions = permissions.stream()
                .filter(x -> {
            return x != null;
        }).collect(Collectors.toSet());

        System.err.println(permissions);

        // 合并两个set
        roles.addAll(permissions);

        *//**
         * 将泛型为String 的set
         * 改为泛型为 SimpleGrantedAuthority的set
         *//*

        Set<SimpleGrantedAuthority> authorities = roles.stream()
                .map(name -> {
                    return new SimpleGrantedAuthority(name);
        }).collect(Collectors.toSet());

        // 授权数据的封装
        admin.setAuthorities(authorities);
*/
        return admin;
    }
}

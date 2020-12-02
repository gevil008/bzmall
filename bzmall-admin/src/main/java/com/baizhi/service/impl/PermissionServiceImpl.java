package com.baizhi.service.impl;

import com.baizhi.entity.BzAdmin;
import com.baizhi.service.BzRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("ss")
public class PermissionServiceImpl {

    @Autowired
    private BzRoleService bzRoleService;

    public boolean hasRole(String role){
        return hasAnyRole(role);
    }

    public boolean hasAnyRole(String... roles) {
        /**
         * 获取角色信息
         */
        Set<String> roleSet = bzRoleService.getRolesByUsername(getUsername());

        /**
         * 校验角色
         */
        for (String role : roles) {
            if (roleSet.contains(role)) {
                return true;
            }
        }
        return false;
    }


    public boolean hasPermission(String permission){
        return hasAnyPermission(permission);
    }

    public boolean hasAnyPermission(String... permissions) {
        /**
         * 获取角色信息
         */
        Set<String> roleSet = bzRoleService.getPermissionsByUsername(getUsername());

        /**
         * 校验角色
         */
        for (String permission : permissions) {
            if (roleSet.contains(permission)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取当前认证的用户的用户名
     */
    public String getUsername(){
        /**
         * SecurityContextHolder 获取Security相关的数据 包含用户对象
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BzAdmin principal = (BzAdmin) authentication.getPrincipal();
        return principal.getUsername();
    }
}

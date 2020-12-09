package com.baizhi.service;

import com.baizhi.entity.BzRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

public interface BzRoleService extends IService<BzRole> {
    /**
     * 根据用户名查询角色名称
     */
    Set<String> getRolesByUsername(String username);
    /**
     * 根据用户名查询权限字符串
     */
    Set<String> getPermissionsByUsername(String username);
}

package com.baizhi.service.impl;

import com.baizhi.dao.*;
import com.baizhi.entity.*;
import com.baizhi.service.BzRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BzRoleServiceImpl implements BzRoleService {

    @Autowired
    private BzAdminMapper bzAdminMapper;
    @Autowired
    private BzAdminRoleMapper bzAdminRoleMapper;
    @Autowired
    private BzRoleMapper bzRoleMapper;
    @Autowired
    private BzRoleResourceMapper bzRoleResourceMapper;
    @Autowired
    private BzMenuMapper bzMenuMapper;

    @Override
    public Set<String> getRolesByUsername(String username) {

        BzAdmin bzAdmin = bzAdminMapper.selectOne(
                new QueryWrapper<BzAdmin>()
                        .eq("username", username));

        List<BzAdminRole> roleList = bzAdminRoleMapper.selectList(
                new QueryWrapper<BzAdminRole>()
                        .eq("admin_id", bzAdmin.getId()));

        List<Integer> integers = roleList.stream().map(item -> {
            return item.getRoleId();
        }).collect(Collectors.toList());

        List<BzRole> bzRoles = bzRoleMapper.selectBatchIds(integers);

        Set<String> collect = bzRoles.stream().map(role -> {
            return role.getRoleName();
        }).collect(Collectors.toSet());

        return collect;
    }

    @Override
    public Set<String> getPermissionsByUsername(String username) {

        Set<String> byUsername = getRolesByUsername(username);

        List<BzRoleResource> resources = bzRoleResourceMapper.selectList(
                new QueryWrapper<BzRoleResource>()
                        .in("role_name", byUsername));

        List<Integer> list = resources.stream().map(item -> {
            return item.getResourceId();
        }).collect(Collectors.toList());

        List<BzMenu> menus = bzMenuMapper.selectBatchIds(list);

        Set<String> collect = menus.stream().map(role -> {
            return role.getPerms();
        }).collect(Collectors.toSet());

        return collect;
    }
}

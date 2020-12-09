package com.baizhi.service.impl;

import com.baizhi.entity.BzAdminRole;
import com.baizhi.dao.BzAdminRoleMapper;
import com.baizhi.service.BzAdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lby
 * @since 2020-11-24
 */
@Service
public class BzAdminRoleServiceImpl extends ServiceImpl<BzAdminRoleMapper, BzAdminRole> implements BzAdminRoleService {

    @Autowired
    private BzAdminRoleMapper bzAdminRoleMapper;

    @Override
    public boolean updateRoleId(Integer adminId, Integer roleId) {
        return bzAdminRoleMapper.updateRoleId(adminId,roleId);
    }
}

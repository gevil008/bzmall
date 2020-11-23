package com.baizhi.service.impl;

import com.baizhi.dao.BzAdminMapper;
import com.baizhi.entity.BzAdmin;
import com.baizhi.service.BzAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BzAdminServiceImpl extends ServiceImpl<BzAdminMapper, BzAdmin> implements BzAdminService {
    @Autowired
    private BzAdminMapper adminDao;

    @Override
    public List<BzAdmin> selectAllAdmin() {
        return adminDao.selectList(null);
    }
}

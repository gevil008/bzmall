package com.baizhi.service.impl;

import com.baizhi.dao.BzAdminDao;
import com.baizhi.entity.BzAdmin;
import com.baizhi.service.BzAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BzAdminServiceImpl implements BzAdminService {
    @Autowired
    private BzAdminDao adminDao;

    @Override
    public List<BzAdmin> selectAllAdmin() {
        return adminDao.selectList(null);
    }
}

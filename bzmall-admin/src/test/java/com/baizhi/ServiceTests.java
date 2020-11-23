package com.baizhi;

import com.baizhi.entity.BzAdmin;
import com.baizhi.service.BzAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceTests extends AppRunTests{
    @Autowired
    private BzAdminService adminService;

    @Test
    public void test1(){
        for (BzAdmin bzAdmin : adminService.selectAllAdmin()) {
            System.out.println(bzAdmin);
        }
    }
    @Test
    public void login(){
        BzAdmin bzAdmin = new BzAdmin();
        bzAdmin.setUsername("lisi");
        bzAdmin.setPassword("123456");
        BzAdmin one = adminService.getOne(new QueryWrapper<BzAdmin>().eq("username", bzAdmin.getUsername()).eq("password",bzAdmin.getPassword()));
        System.err.println(one);
        if (one == null){
            System.err.println("用户名或者密码错误");
            return;
        }

    }
}

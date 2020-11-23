package com.baizhi;

import com.baizhi.entity.BzAdmin;
import com.baizhi.service.BzAdminService;
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
}

package com.baizhi;

import com.baizhi.dao.BzAdminMapper;
import com.baizhi.dao.BzMenuMapper;
import com.baizhi.dao.CmfzUserDao;
import com.baizhi.dao.PmsBrandMapper;
import com.baizhi.entity.BzAdmin;
import com.baizhi.entity.BzMenu;
import com.baizhi.service.BzAdminService;
import com.baizhi.service.BzRoleService;
import com.baizhi.service.PmsBrandService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DaoTests extends AppRunTests{
    @Autowired
    private BzMenuMapper bzMenuMapper;

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Autowired
    private PmsBrandService pmsBrandService;

    @Autowired
    private BzAdminService bzAdminService;

    @Autowired
    private CmfzUserDao userDao;

    @Autowired
    private BzAdminMapper bzAdminMapper;

    @Autowired
    private BzRoleService bzRoleService;
    @Test
    public void test(){
        List<BzMenu> menus = bzMenuMapper.selectMenusbySql();
        menus.forEach(System.err::println);
    }

    @Test
    public void test2(){
        try {
            // pmsBrandMapper.updateShowStatus(1,1);
            pmsBrandService.updateShowStatus(1,1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("出错了");
        }
    }

    @Test
    public void test3(){
        List<BzAdmin> bzAdmins = bzAdminService.selectAllAdmin();
        bzAdmins.forEach(System.err::println);
    }

}

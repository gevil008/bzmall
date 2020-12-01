package com.baizhi;

import com.baizhi.entity.BzAdmin;
import com.baizhi.service.BzAdminService;
import com.baizhi.service.BzRoleService;
import com.baizhi.service.PmsCategoryService;
import com.baizhi.vo.ZTreeNode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class ServiceTests extends AppRunTests{
    @Autowired
    private BzAdminService adminService;

    @Autowired
    private PmsCategoryService pmsCategoryService;

    @Autowired
    private BzRoleService bzRoleService;

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

    @Test
    public void testShowPage(){
        Page<BzAdmin> page = new Page<>(1,5);
        Page<BzAdmin> page1 = adminService.page(page);
        List<BzAdmin> records = page1.getRecords();
        records.forEach(System.err::println);
        long total = page1.getTotal();
        System.err.println(total);

    }

    @Test
    public void test3(){
        List<ZTreeNode> nodes = pmsCategoryService.selectCategoryByZtree();
        nodes.forEach(System.err::println);
    }

    @Test
    public void test4(){
        boolean b = adminService.addBzAdmin(new BzAdmin(null, "aaa", "123456"));
        System.err.println(b);
    }

    @Test
    public void test5(){
        Set<String> set = bzRoleService.getRolesByUsername("zhangsan");
        System.err.println(set);
        Set<String> lisi = bzRoleService.getPermissionsByUsername("zhangsan");
        System.err.println(lisi);
    }
}

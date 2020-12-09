package com.baizhi.controller;


import com.baizhi.entity.BzRole;
import com.baizhi.service.BzRoleService;
import com.baizhi.vo.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caoyafei
 * @since 2020-12-01
 */
@RestController
@RequestMapping("/role")
public class BzRoleController {
    @Autowired
    private BzRoleService bzRoleService;

    @RequestMapping("/roles")
    public R selectAll(){
        List<BzRole> roles = bzRoleService.list();
        System.err.println("------------查询角色信息------------");
        roles.forEach(System.err::println);
        return R.ok().put("success",roles);
    }

    @GetMapping("/roles")
    public R showPage(@RequestParam(defaultValue = "1")Integer page,
                      @RequestParam(defaultValue = "5")Integer limit,
                      String search){
        System.err.println(page);
        System.err.println(limit);
        Page<BzRole> sectionPage;
        if (search != null){
            QueryWrapper<BzRole> queryWrapper = new QueryWrapper();
            queryWrapper.like("role_name",search);
            sectionPage = bzRoleService.page(new Page<>(page,limit),queryWrapper);
        } else {
            sectionPage = bzRoleService.page(new Page<>(page,limit));
        }
        long total = sectionPage.getTotal();
        List<BzRole> records = sectionPage.getRecords();
        return R.ok().put("data",records).put("count",total).put("code",0);
    }
}

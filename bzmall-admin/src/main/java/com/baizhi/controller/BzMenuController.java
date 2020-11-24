package com.baizhi.controller;


import com.baizhi.entity.BzMenu;
import com.baizhi.service.BzMenuService;
import com.baizhi.vo.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-23
 */
@RestController
@RequestMapping("menu")
public class BzMenuController {

    @Autowired
    private BzMenuService bzMenuService;

    @RequestMapping("/getMenuList")
    public List<BzMenu> selectMenusbySql(){
        return bzMenuService.selectMenusbySql();
    }

    @RequestMapping("/getZTreeNodes")
    public List<ZTreeNode> selectMenusbyZtree(){
        return bzMenuService.selectMenusbyZtree();
    }
}

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
@RequestMapping("/menu")
// @Slf4j
public class BzMenuController {

    @Autowired
    private BzMenuService bzMenuService;

    @RequestMapping("/getMenuList")
    public List<BzMenu> selectMenusbySql(){
        /*
        // 自定义日志测试
        String flag = "成功";
        List<BzMenu> menus = bzMenuService.selectMenusbySql();
        menus.forEach(System.err::println);
        log.info("查询：{}查询到菜单数据为：{}",flag,menus);
        return menus;*/
        return bzMenuService.selectMenusbySql();
    }

    @RequestMapping("/getZTreeNodes")
    public List<ZTreeNode> selectMenusbyZtree(){
        return bzMenuService.selectMenusbyZtree();
    }
}

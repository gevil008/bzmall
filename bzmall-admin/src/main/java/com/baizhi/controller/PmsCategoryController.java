package com.baizhi.controller;


import com.baizhi.service.PmsCategoryService;
import com.baizhi.vo.CascaderNodeVo;
import com.baizhi.vo.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品三级分类 前端控制器
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/cate")
public class PmsCategoryController {

    @Autowired
    private PmsCategoryService pmsCategoryService;

    @PreAuthorize("@ss.hasAnyRole('admin','superadmin')")
    @RequestMapping("/getZTreeNodes")
    public List<ZTreeNode> selectCategoryByZtree(){
        return pmsCategoryService.selectCategoryByZtree();
    }

    @GetMapping("/getCascaderNodeVos")
    public List<CascaderNodeVo> getCascaderNodeVos(){
        return pmsCategoryService.getCascaderNodeVos();
    }
}

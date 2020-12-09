package com.baizhi.controller;


import com.baizhi.service.PmsAttrService;
import com.baizhi.vo.AttrVo;
import com.baizhi.vo.CascaderNodeVo;
import com.baizhi.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author caoyafei
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/attr")
public class PmsAttrController {

    @Autowired
    private PmsAttrService pmsAttrService;


    @GetMapping("/base/selectAttrBySize")
    public R selectAttrBySize(Integer page,Integer limit,Long categoryId){
        return pmsAttrService.selectAttrBySize(page,limit,categoryId);
    }

    @GetMapping("/base/getAttrGroupCascaderNodeVo")
    public List<CascaderNodeVo> getAttrGroupCascaderNodeVo(Long categoryId){
        return pmsAttrService.getAttrGroupCascaderNodeVo(categoryId);
    }

    @PostMapping("/addAttr")
    public R addAttr(AttrVo vo){
        System.err.println("-----"+vo+"-----------");
        pmsAttrService.addAttr(vo);
        return R.ok();
    }

}

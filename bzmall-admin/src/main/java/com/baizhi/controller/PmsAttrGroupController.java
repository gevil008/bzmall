package com.baizhi.controller;


import com.baizhi.service.PmsAttrGroupService;
import com.baizhi.vo.PmsAttrGroupVo;
import com.baizhi.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 属性分组 前端控制器
 * </p>
 *
 * @author caoyafei
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/attr-group")
public class PmsAttrGroupController {

    @Autowired
    private PmsAttrGroupService pmsAttrGroupService;

    @GetMapping("/selectAttrGroupBySizeAndCateId")
    public R selectAttrGroupBySizeAndCateId(Integer page,Integer limit,Long categoryId){
        return pmsAttrGroupService.selectAttrGroupBySizeAndCateId(page,limit,categoryId);
    }

    @PostMapping("/addAttrGroup")
    public R addAttrGroup(PmsAttrGroupVo vo){
        System.err.println(vo);
        if (pmsAttrGroupService.addAttrGroup(vo)){
            return R.ok().put("result","success");
        } else {
            return R.error().put("result","error");
        }
    }
}

package com.baizhi.controller;


import com.baizhi.entity.PmsBrand;
import com.baizhi.enums.LogTypeEnum;
import com.baizhi.log.LogAnnotation;
import com.baizhi.service.PmsBrandService;
import com.baizhi.util.PinYinUpperCase;
import com.baizhi.vo.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-24
 */
@RestController
@RequestMapping("brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    @GetMapping("/brands")
    @LogAnnotation(content = "商品系统-品牌管理查询")
    public R showPage(@RequestParam(defaultValue = "1")Integer page,
                      @RequestParam(defaultValue = "5")Integer limit,
                      String search){
        Page<PmsBrand> sectionPage = null;
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper();
        if (search != null){
            queryWrapper.like("username",search);
            sectionPage = pmsBrandService.page(new Page<>(page,limit),queryWrapper);
        } else {
            queryWrapper.orderByAsc("sort");
            sectionPage = pmsBrandService.page(new Page<>(page,limit),queryWrapper);
        }
        long total = sectionPage.getTotal();
        List<PmsBrand> records = sectionPage.getRecords();
        return R.ok().put("data",records).put("count",total).put("code",0);
    }

    @DeleteMapping("/brands/{id}")
    @LogAnnotation(type = LogTypeEnum.DELETE,content = "商品系统-品牌管理删除单条商品")
    public R deleteOne(@PathVariable("id")Integer id){
        try {
            pmsBrandService.removeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().put("status","error");
        }
        return R.ok().put("status","success");
    }

    @PostMapping("/brands")
    @LogAnnotation(type = LogTypeEnum.ADD,content = "商品系统-品牌管理添加一个商品")
    public R addOne(@RequestBody PmsBrand pmsBrand){
        /**
         * PinYinUpperCase.getPinYinUpperCase(String)
         * 获取文字的拼音首字母
         */
        pmsBrand.setFirstLetter(PinYinUpperCase.getPinYinUpperCase(pmsBrand.getName()));
        if (pmsBrandService.save(pmsBrand)){
            return R.ok().put("status","success");
        } else {
            return R.error().put("status","error");
        }
    }

    @DeleteMapping("/brands")
    @LogAnnotation(type = LogTypeEnum.DELETE,content = "商品系统-品牌管理删除多条商品")
    public R deleteMay(@RequestBody List<Integer> ids){
        if (pmsBrandService.removeByIds(ids)){
            return R.ok().put("status","success");
        } else {
            return R.error().put("status","error");
        }
    }

    @PutMapping("/brands")
    @LogAnnotation(type = LogTypeEnum.UPDATE,content = "商品系统-品牌管理修改单个商品")
    public R updateOne(@RequestBody PmsBrand pmsBrand){
        pmsBrand.setFirstLetter(PinYinUpperCase.getPinYinUpperCase(pmsBrand.getName()));
        if (pmsBrandService.updateById(pmsBrand)){
            return R.ok().put("status","success");
        } else {
            return R.error().put("status","error");
        }
    }
    @PutMapping("/brands/{brandId}/{statusId}")
    public R updateStatus(@PathVariable("brandId") Integer brandId,@PathVariable("statusId")Integer statusId){
        try {
            pmsBrandService.updateShowStatus(brandId,statusId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().put("status","error");
        }
        return R.ok().put("status","success");
    }
}

package com.baizhi.controller;


import com.baizhi.entity.PmsBrand;
import com.baizhi.service.PmsBrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map showPage(@RequestParam(defaultValue = "1")Integer page,
                        @RequestParam(defaultValue = "5")Integer limit,
                        String search){
        System.err.println(page);
        System.err.println(limit);
        Page<PmsBrand> sectionPage = null;
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper();
        if (search != null){
            queryWrapper.like("username",search);
            sectionPage = pmsBrandService.page(new Page<>(page,limit),queryWrapper);
        } else {
            queryWrapper.eq("show_status",0);
            sectionPage = pmsBrandService.page(new Page<>(page,limit),queryWrapper);
        }
        long total = sectionPage.getTotal();
        List<PmsBrand> records = sectionPage.getRecords();

        Map map = new HashMap();
        map.put("data",records);
        map.put("count",total);
        map.put("code",0);

        return map;
    }

    @DeleteMapping("/brands/{id}")
    public Map deleteOne(@PathVariable("id")Integer id){
        System.err.println(id);
        Map map = new HashMap();
        try {
            pmsBrandService.removeById(id);
            map.put("status","success");
        } catch (Exception e) {
            map.put("status","error");
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/brands")
    public Map addOne(@RequestBody PmsBrand pmsBrand){
        System.err.println(pmsBrand);
        Map map = new HashMap();
        boolean save = pmsBrandService.save(pmsBrand);
        System.err.println(save);
        if (save){
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @DeleteMapping("/brands")
    public Map deleteMay(@RequestBody List<Integer> ids){
        ids.forEach(System.err::println);
        boolean b = pmsBrandService.removeByIds(ids);
        Map map = new HashMap();
        if (b){
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @PutMapping("/brands")
    public Map updateOne(@RequestBody PmsBrand pmsBrand){
        System.err.println(pmsBrand);
        boolean b = pmsBrandService.updateById(pmsBrand);
        Map map = new HashMap();
        if (b){
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }
}

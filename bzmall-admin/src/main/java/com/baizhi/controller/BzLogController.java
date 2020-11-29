package com.baizhi.controller;


import com.baizhi.entity.BzLog;
import com.baizhi.service.BzLogService;
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
 * @since 2020-11-29
 */
@RestController
@RequestMapping("/log")
public class BzLogController {

    @Autowired
    private BzLogService logService;

    @GetMapping("/logs")
    public R showPage(@RequestParam(defaultValue = "1")Integer page,
                      @RequestParam(defaultValue = "5")Integer limit,
                      String search){
        Page<BzLog> sectionPage;
        QueryWrapper<BzLog> queryWrapper = new QueryWrapper();
        if (search != null){
            queryWrapper.like("username",search).or().like("log_type",search);
            sectionPage = logService.page(new Page<>(page,limit),queryWrapper);
        } else {
            sectionPage = logService.page(new Page<>(page,limit),queryWrapper);
        }
        long total = sectionPage.getTotal();
        List<BzLog> records = sectionPage.getRecords();
        return R.ok().put("data",records).put("count",total).put("code",0);

    }
}

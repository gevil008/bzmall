package com.baizhi.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.baizhi.entity.BzAdmin;
import com.baizhi.service.BzAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-23
 */
@RestController
@RequestMapping("admin")
public class BzAdminController {

    @Autowired
    private BzAdminService bzAdminService;

    LineCaptcha lineCaptcha = null;

    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpServletResponse response, HttpServletRequest request) {
        lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        try {
            request.getSession().setAttribute("CAPTCHA_KEY", lineCaptcha.getCode());
            response.setContentType("image/png");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            lineCaptcha.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/login/{code}")
    public Map login(@RequestBody BzAdmin bzAdmin,@PathVariable("code") String code){
        Map map = new HashMap();
        if(lineCaptcha.verify(code)){
            BzAdmin one = bzAdminService.getOne(new QueryWrapper<BzAdmin>().eq("username", bzAdmin.getUsername()).eq("password", bzAdmin.getPassword()));
            if (one != null){
                map.put("status","success");
            }else {
                map.put("status","error");
            }
        }else {
            map.put("Verfystatus","Wrong");
        }
        return map;
    }

    @GetMapping("/admins")
    public Map showPage(@RequestParam(defaultValue = "1")Integer page,
                        @RequestParam(defaultValue = "5")Integer limit,
                        String search){
        System.err.println(page);
        System.err.println(limit);
        Page<BzAdmin> sectionPage = null;
        if (search != null){
            QueryWrapper<BzAdmin> queryWrapper = new QueryWrapper();
            queryWrapper.like("username",search);
            sectionPage = bzAdminService.page(new Page<>(page,limit),queryWrapper);
        } else {
            sectionPage = bzAdminService.page(new Page<>(page,limit));
        }
        long total = sectionPage.getTotal();
        List<BzAdmin> records = sectionPage.getRecords();

        Map map = new HashMap();
        map.put("data",records);
        map.put("count",total);
        map.put("code",0);

        return map;
    }

    @DeleteMapping("/admins/{id}")
    public Map deleteOne(@PathVariable("id")Integer id){
        System.err.println(id);
        Map map = new HashMap();
        try {
            bzAdminService.removeById(id);
            map.put("status","success");
        } catch (Exception e) {
            map.put("status","error");
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/admins")
    public Map addOne(@RequestBody BzAdmin bzAdmin){
        System.err.println(bzAdmin);
        Map map = new HashMap();
        boolean save = bzAdminService.save(bzAdmin);
        System.err.println(save);
        if (save){
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @DeleteMapping("/admins")
    public Map deleteMay(@RequestBody List<Integer> ids){
        ids.forEach(System.err::println);
        boolean b = bzAdminService.removeByIds(ids);
        Map map = new HashMap();
        if (b){
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

    @PutMapping("/admins")
    public Map updateOne(@RequestBody BzAdmin bzAdmin){
        System.err.println(bzAdmin);
        boolean b = bzAdminService.updateById(bzAdmin);
        Map map = new HashMap();
        if (b){
            map.put("status","success");
        } else {
            map.put("status","error");
        }
        return map;
    }

}

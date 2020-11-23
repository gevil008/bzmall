package com.baizhi.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.baizhi.entity.BzAdmin;
import com.baizhi.service.BzAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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



}

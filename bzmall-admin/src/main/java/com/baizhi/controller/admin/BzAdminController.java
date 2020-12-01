package com.baizhi.controller.admin;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.baizhi.entity.BzAdmin;
import com.baizhi.enums.LogTypeEnum;
import com.baizhi.log.LogAnnotation;
import com.baizhi.service.BzAdminService;
import com.baizhi.vo.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
    public void getCaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        session.setAttribute("code",lineCaptcha.getCode());

        System.err.println(lineCaptcha.getCode());

        lineCaptcha.write(response.getOutputStream());
    }

    @PostMapping("/login/{code}")
    public R login(@RequestBody BzAdmin bzAdmin,@PathVariable("code") String code){
        if(lineCaptcha.verify(code)){
            BzAdmin one = bzAdminService.getOne(new QueryWrapper<BzAdmin>().eq("username", bzAdmin.getUsername()).eq("password", bzAdmin.getPassword()));
            if (one != null){
                return R.ok().put("status","success");
            }else {
                return R.error().put("status","error");
            }
        }else {
            return R.error().put("Verfystatus","Wrong");
        }
    }

    @PreAuthorize("hasAnyRole('admin','superadmin')")
    @GetMapping("/admins")
    @LogAnnotation(type = LogTypeEnum.SELECT,content = "查询系统管理-管理员列表")
    public R showPage(@RequestParam(defaultValue = "1")Integer page,
                        @RequestParam(defaultValue = "5")Integer limit,
                        String search){
        System.err.println(page);
        System.err.println(limit);
        Page<BzAdmin> sectionPage;
        if (search != null){
            QueryWrapper<BzAdmin> queryWrapper = new QueryWrapper();
            queryWrapper.like("username",search);
            sectionPage = bzAdminService.page(new Page<>(page,limit),queryWrapper);
        } else {
            sectionPage = bzAdminService.page(new Page<>(page,limit));
        }
        long total = sectionPage.getTotal();
        List<BzAdmin> records = sectionPage.getRecords();
        return R.ok().put("data",records).put("count",total).put("code",0);
    }

    @DeleteMapping("/admins/{id}")
    @LogAnnotation(type = LogTypeEnum.DELETE,content = "删除单条管理员信息")
    public R deleteOne(@PathVariable("id")Integer id){
        System.err.println(id);
        try {
            bzAdminService.removeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().put("status","error");
        }
        return R.ok().put("status","success");
    }

    @PostMapping("/admins")
    @LogAnnotation(type = LogTypeEnum.ADD,content = "添加管理员用户")
    public R addOne(@RequestBody BzAdmin bzAdmin){
        System.err.println(bzAdmin);
        if (bzAdminService.save(bzAdmin)){
            return R.ok().put("status","success");
        } else {
            return R.error().put("status","error");
        }
    }

    @DeleteMapping("/admins")
    @LogAnnotation(type = LogTypeEnum.DELETE,content = "批量删除管理员用户")
    public R deleteMay(@RequestBody List<Integer> ids){
        ids.forEach(System.err::println);
        if (bzAdminService.removeByIds(ids)){
            return R.ok().put("status","success");
        } else {
            return R.error().put("status","error");
        }
    }

    @PutMapping("/admins")
    @LogAnnotation(type = LogTypeEnum.UPDATE,content = "修改管理员用户信息")
    public R updateOne(@RequestBody BzAdmin bzAdmin){
        System.err.println(bzAdmin);
        if (bzAdminService.updateById(bzAdmin)){
            return R.ok().put("status","success");
        } else {
            return R.error().put("status","error");
        }
    }
    /**
     * 接受成功的请求
     * @return
     */
    @RequestMapping("/successForward")
    @ResponseBody
    public R successForward(){
        System.err.println("ok");
        return R.ok();
    }

    /**
     * 接受失败的请求
     * @return
     */
    @RequestMapping("/failureForward")
    @ResponseBody
    public R failureForward(){
        System.err.println("no");
        return R.error();
    }
}

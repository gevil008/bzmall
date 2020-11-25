package com.baizhi.api.controller;

import com.baizhi.common.AddGroups;
import com.baizhi.entity.TestEntity;
import com.baizhi.vo.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * 单个参数校验 @Validated 需要加在类上
 */
@Validated
@RestController
@RequestMapping("/admin")
public class ApiAdminController {

    /**
     * 单个参数
     * @Validated 开启参数校验
     *
     * @Validated(AddGroups.class) 采用分组校验
     *
     * 实体类属性上所有没有分组的校验注解是不会生效的
     */
    @RequestMapping("/addAdmin")
    public R addAdmin(@NotEmpty String username, @NotEmpty String password){
        System.out.println(username);
        System.out.println(password);
        return R.ok();
    }

    @RequestMapping("/addAdmins")
    public R addAdmin2(@Validated(AddGroups.class) TestEntity testEntity){
        System.err.println(testEntity);
        return R.ok();
    }
}

package com.baizhi.entity;

import com.baizhi.common.AddGroups;
import com.baizhi.common.UpdateGroups;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class TestEntity {

    /**
     * 实体类上使用 校验注解 设置分组
     * 修改方法 id属性不能为null
     * 添加方法 id属性不能有值
     *
     * message 自定义信息
     * groups  指定分组
     *
     * 使用分组校验，每个注解都需要指定分组
     */

    @NotEmpty(groups = UpdateGroups.class,message = "修改方法 id属性不能为null")
    @Null(groups = AddGroups.class,message = "添加方法 id属性不能有值")
    private Integer id;

    @NotEmpty(groups = {UpdateGroups.class,AddGroups.class})
    private String name;

    @NotEmpty(groups = {UpdateGroups.class,AddGroups.class})
    private String password;

    @NotEmpty(groups = {UpdateGroups.class,AddGroups.class})
    @Size(max = 11,min = 11,message = "长度必须是11位",groups = {UpdateGroups.class,AddGroups.class})
    private String phone;

    @Email(groups = {UpdateGroups.class,AddGroups.class})
    private String email;
}

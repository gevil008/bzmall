package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("cmfz_user")
@Data
public class CmfzUser implements Serializable {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;
    private String userTelphone;
    private String userPassword;
    private String userImage;
    private String userNickname;
    private String userName;
    private String userSex;
    private String userAutograph;
    private String userProvince;
    private String userCity;
    private Integer guruId;
    private Integer userStatus;
    private Date userCreateDate;

}
package com.baizhi.dao;

import com.baizhi.entity.CmfzUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface CmfzUserDao extends BaseMapper<CmfzUser> {
    int add(List<CmfzUser> list);
}

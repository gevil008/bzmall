package com.baizhi.service;

import com.baizhi.entity.BzAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BzAdminService extends IService<BzAdmin> {
    List<BzAdmin> selectAllAdmin();
}

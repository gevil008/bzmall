package com.baizhi.service;

import com.baizhi.entity.BzMenu;
import com.baizhi.vo.ZTreeNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author lby
 * @since 2020-11-23
 */
public interface BzMenuService extends IService<BzMenu> {
    List<BzMenu> selectMenusbySql();

    List<ZTreeNode> selectMenusbyZtree();
}

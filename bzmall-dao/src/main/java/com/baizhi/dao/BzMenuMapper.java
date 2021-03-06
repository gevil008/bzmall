package com.baizhi.dao;

import com.baizhi.entity.BzMenu;
import com.baizhi.vo.ZTreeNode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author lby
 * @since 2020-11-23
 */
public interface BzMenuMapper extends BaseMapper<BzMenu> {
    List<BzMenu> selectMenusbySql();

    // 直接将菜单数据封装到ZtreeNode中
    List<ZTreeNode> selectMenusbyZtree();
}

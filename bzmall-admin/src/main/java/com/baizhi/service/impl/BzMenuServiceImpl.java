package com.baizhi.service.impl;

import com.baizhi.entity.BzMenu;
import com.baizhi.dao.BzMenuMapper;
import com.baizhi.log.LogAnnotation;
import com.baizhi.service.BzMenuService;
import com.baizhi.vo.ZTreeNode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-23
 */
@Service
public class BzMenuServiceImpl extends ServiceImpl<BzMenuMapper, BzMenu> implements BzMenuService {

    @Autowired
    private BzMenuMapper bzMenuMapper;

    @LogAnnotation(content = "查询菜单数据")
    @Override
    public List<BzMenu> selectMenusbySql() {
        return bzMenuMapper.selectMenusbySql();
    }

    /*@Override
    // 直接将数据封装到ZtreeNode中来实现
    public List<ZTreeNode> selectMenusbyZtree() {
        return bzMenuMapper.selectMenusbyZtree();
    }*/
    /**
     * 通过流实现
     * @return
     */
    @Override
    @LogAnnotation(content = "查询菜单数据，通过流查询")
    public List<ZTreeNode> selectMenusbyZtree() {
        /**
         * 查询所有菜单数据
         */
        List<BzMenu> menus = this.list();

        /**
         * 找到所有的一级菜单，封装到Node中
         */
        List<ZTreeNode> nodes = menus.stream()
                .filter(bzMenu -> {
                    return bzMenu.getType() == 0;
                })
                .map(bzMenu -> {
                    ZTreeNode zTreeNode = new ZTreeNode();
                    zTreeNode.setId(bzMenu.getMenuId());
                    zTreeNode.setName(bzMenu.getName());
                    zTreeNode.setChildren(getChildrenNodes(bzMenu.getMenuId(), menus));
                    return zTreeNode;
                }).collect(Collectors.toList());
        return nodes;
    }

    private List<ZTreeNode> getChildrenNodes(Long menuId, List<BzMenu> menus) {
        return menus.stream()
                .filter(bzMenu -> {
                    return bzMenu.getParentId().equals(menuId);
                })
                .map(bzMenu -> {
                    ZTreeNode zTreeNode = new ZTreeNode();
                    zTreeNode.setId(bzMenu.getMenuId());
                    zTreeNode.setName(bzMenu.getName());
                    zTreeNode.setChildren(getChildrenNodes(bzMenu.getMenuId(), menus));
                    return zTreeNode;
                }).collect(Collectors.toList());
    }

}

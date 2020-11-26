package com.baizhi.service.impl;

import com.baizhi.entity.PmsCategory;
import com.baizhi.dao.PmsCategoryMapper;
import com.baizhi.service.PmsCategoryService;
import com.baizhi.vo.ZTreeNode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-26
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {

    @Override
    public List<ZTreeNode> selectCategoryByZtree() {
        /**
         * 查询所有菜单数据
         */
        List<PmsCategory> categories = this.list();
        /**
         * 找到所有的一级菜单，封装到node中
         */
        List<ZTreeNode> nodes = categories.stream()
                .filter(pmsCategory -> {
                    return pmsCategory.getParentCid() == 0;
                })
                .map(pmsCategory -> {
                    ZTreeNode zTreeNode = new ZTreeNode();
                    zTreeNode.setId(pmsCategory.getCatId());
                    zTreeNode.setName(pmsCategory.getName());
                    zTreeNode.setChildren(getChildrenNodes(pmsCategory.getCatId(), categories));
                    return zTreeNode;
                }).collect(Collectors.toList());
        return nodes;
    }

    private List<ZTreeNode> getChildrenNodes(Long catId, List<PmsCategory> categories) {
        return categories.stream()
                .filter(pmsCategory -> {
                    return pmsCategory.getParentCid().equals(catId);
                })
                .map(pmsCategory -> {
                    ZTreeNode zTreeNode = new ZTreeNode();
                    zTreeNode.setId(pmsCategory.getCatId());
                    zTreeNode.setName(pmsCategory.getName());
                    zTreeNode.setChildren(getChildrenNodes(pmsCategory.getCatId(), categories));
                    return zTreeNode;
                }).collect(Collectors.toList());
    }


}
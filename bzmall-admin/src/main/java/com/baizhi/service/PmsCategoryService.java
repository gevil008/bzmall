package com.baizhi.service;

import com.baizhi.entity.PmsCategory;
import com.baizhi.vo.ZTreeNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-26
 */
public interface PmsCategoryService extends IService<PmsCategory> {
    List<ZTreeNode> selectCategoryByZtree();
}

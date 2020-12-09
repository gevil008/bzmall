package com.baizhi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baizhi.dao.PmsAttrGroupMapper;
import com.baizhi.dao.PmsCategoryMapper;
import com.baizhi.entity.PmsAttrGroup;
import com.baizhi.entity.PmsCategory;
import com.baizhi.service.PmsAttrGroupService;
import com.baizhi.vo.PmsAttrGroupVo;
import com.baizhi.vo.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 属性分组 服务实现类
 * </p>
 *
 * @author caoyafei
 * @since 2020-12-09
 */
@Service
public class PmsAttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupMapper, PmsAttrGroup> implements PmsAttrGroupService {

    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    @Override
    public R selectAttrGroupBySizeAndCateId(Integer page, Integer limit, Long categoryId) {
        Page<PmsAttrGroup> groupPage = this.page(
                new Page<PmsAttrGroup>(page, limit),
                new QueryWrapper<PmsAttrGroup>().eq("category_id", categoryId)
        );
        return R.ok().put("data",groupPage.getRecords()).put("count",groupPage.getTotal());
    }

    @Override
    public boolean addAttrGroup(PmsAttrGroupVo vo) {
        PmsAttrGroup attrGroup = new PmsAttrGroup();

        BeanUtil.copyProperties(vo,attrGroup);

        PmsCategory pmsCategory = pmsCategoryMapper.selectOne(
                new QueryWrapper<PmsCategory>()
                        .eq("name", vo.getCategoryName())
                        .eq("cat_level", 3)
        );
        attrGroup.setCategoryId(pmsCategory.getCatId());
        return this.save(attrGroup);
    }
}

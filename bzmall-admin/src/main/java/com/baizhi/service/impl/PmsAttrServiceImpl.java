package com.baizhi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baizhi.dao.PmsAttrMapper;
import com.baizhi.entity.PmsAttr;
import com.baizhi.entity.PmsAttrAttrgroupRelation;
import com.baizhi.entity.PmsAttrGroup;
import com.baizhi.service.PmsAttrAttrgroupRelationService;
import com.baizhi.service.PmsAttrGroupService;
import com.baizhi.service.PmsAttrService;
import com.baizhi.vo.AttrVo;
import com.baizhi.vo.CascaderNodeVo;
import com.baizhi.vo.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author caoyafei
 * @since 2020-12-09
 */
@Service
@Transactional
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttr> implements PmsAttrService {

    @Autowired
    private PmsAttrGroupService pmsAttrGroupService;

    @Autowired
    private PmsAttrAttrgroupRelationService pmsAttrAttrgroupRelationService;

    @Override
    public R selectAttrBySize(Integer page, Integer limit, Long categoryId) {
        Page<PmsAttr> attrPage = this.page(
                new Page<PmsAttr>(page, limit),
                new QueryWrapper<PmsAttr>()
                        .eq("category_id", categoryId)
                        .eq("attr_type",0)
        );
        return R.ok().put("data",attrPage.getRecords()).put("count",attrPage.getTotal());
    }

    @Override
    public List<CascaderNodeVo> getAttrGroupCascaderNodeVo(Long categoryId) {
        List<PmsAttrGroup> categories = pmsAttrGroupService.list(new QueryWrapper<PmsAttrGroup>().eq("category_id",categoryId));
        return categories.stream()
                .map(pmsAttrGroup -> {
                    CascaderNodeVo vo = new CascaderNodeVo();
                    vo.setValue(pmsAttrGroup.getAttrGroupId());
                    vo.setLabel(pmsAttrGroup.getAttrGroupName());
                    vo.setChildren(null);
                    return vo;
                }).collect(Collectors.toList());
    }

    @Override
    public void addAttr(AttrVo vo) {
        PmsAttr pmsAttr = new PmsAttr();

        BeanUtil.copyProperties(vo,pmsAttr);
        PmsAttrGroup pmsAttrGroup = pmsAttrGroupService.getById(vo.getAttrGroupId());
        pmsAttr.setCategoryId(pmsAttrGroup.getCategoryId());
        if (this.save(pmsAttr)){
            PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = new PmsAttrAttrgroupRelation();
            pmsAttrAttrgroupRelation.setAttrId(pmsAttr.getAttrId());
            pmsAttrAttrgroupRelation.setAttrGroupId(pmsAttrGroup.getAttrGroupId());
            pmsAttrAttrgroupRelationService.save(pmsAttrAttrgroupRelation);
        }
    }
}

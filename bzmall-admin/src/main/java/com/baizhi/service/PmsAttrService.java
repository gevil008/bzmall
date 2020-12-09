package com.baizhi.service;

import com.baizhi.entity.PmsAttr;
import com.baizhi.vo.AttrVo;
import com.baizhi.vo.CascaderNodeVo;
import com.baizhi.vo.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author caoyafei
 * @since 2020-12-09
 */
public interface PmsAttrService extends IService<PmsAttr> {
    R selectAttrBySize(Integer page,Integer limit,Long categoryId);

    List<CascaderNodeVo> getAttrGroupCascaderNodeVo(Long categoryId);

    void addAttr(AttrVo vo);
}

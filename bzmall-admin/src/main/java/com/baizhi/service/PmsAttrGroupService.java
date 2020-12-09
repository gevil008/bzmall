package com.baizhi.service;

import com.baizhi.entity.PmsAttrGroup;
import com.baizhi.vo.PmsAttrGroupVo;
import com.baizhi.vo.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author caoyafei
 * @since 2020-12-09
 */
public interface PmsAttrGroupService extends IService<PmsAttrGroup> {
    R selectAttrGroupBySizeAndCateId(Integer page,Integer limit,Long categoryId);

    boolean addAttrGroup(PmsAttrGroupVo vo );
}

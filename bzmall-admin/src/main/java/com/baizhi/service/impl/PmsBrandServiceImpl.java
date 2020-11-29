package com.baizhi.service.impl;

import com.baizhi.dao.PmsBrandMapper;
import com.baizhi.entity.PmsBrand;
import com.baizhi.enums.LogTypeEnum;
import com.baizhi.log.LogAnnotation;
import com.baizhi.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-24
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Override
    @LogAnnotation(type = LogTypeEnum.UPDATE,content = "修改品牌商品展示状态")
    public void updateShowStatus(Integer brandId, Integer statusId) {
        pmsBrandMapper.updateShowStatus(brandId,statusId);
    }
}

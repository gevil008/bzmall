package com.baizhi.dao;

import com.baizhi.entity.PmsBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-24
 */
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {

    void updateShowStatus(@Param("brandId")Integer brandId,@Param("statusId")Integer statusId);
}

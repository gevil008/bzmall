package com.baizhi.dao;

import com.baizhi.entity.BzAdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lby
 * @since 2020-11-24
 */
public interface BzAdminRoleMapper extends BaseMapper<BzAdminRole> {
    boolean updateRoleId(@Param("adminId")Integer adminId,@Param("roleId")Integer roleId);
}

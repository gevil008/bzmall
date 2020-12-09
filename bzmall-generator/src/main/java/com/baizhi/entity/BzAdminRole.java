package com.baizhi.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lby
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BzAdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer adminId;

    private Integer roleId;


}

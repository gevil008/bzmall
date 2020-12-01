package com.baizhi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author caoyafei
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BzRoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleName;

    private Integer resourceId;


}

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
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BzAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private Integer status;


}

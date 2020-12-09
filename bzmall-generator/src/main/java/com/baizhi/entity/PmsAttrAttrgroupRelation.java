package com.baizhi.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 属性&属性分组关联
 * </p>
 *
 * @author caoyafei
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsAttrAttrgroupRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性分组id
     */
    private Long attrGroupId;

    /**
     * 属性组内排序
     */
    private Integer attrSort;


}

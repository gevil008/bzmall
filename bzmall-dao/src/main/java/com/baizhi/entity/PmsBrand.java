package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId(value = "brand_id", type = IdType.AUTO)
    private Long brandId;

    /**
     * 名称
     */
    private String name;

    /**
     * 首字母
     */
    private String firstLetter;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否显示[0-不显示；1-显示]
     */
    private Integer showStatus;

    /**
     * 品牌logo
     */
    private String logo;

    /**
     * 品牌描述
     */
    private String brandStory;


}

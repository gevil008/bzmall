package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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

    // private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId(value = "brand_id", type = IdType.AUTO)
    @Excel(name = "品牌id")
    private Long brandId;

    /**
     * 名称
     */
    @Excel(name = "品牌名称")
    private String name;

    /**
     * 首字母
     */
    @Excel(name = "首字母")
    private String firstLetter;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Integer sort;

    /**
     * 是否显示[0-显示；1-不显示]
     */
    @Excel(name = "显示状态",replace = {"不显示_0","显示_1"})
    private Integer showStatus;

    /**
     * 品牌logo
     * Type = 2 该字段类型为图片，imageType=1 （默认可以不填）
     * 表示从file读取，字段类型是个字符串类型 可以用相对路径也可以用绝对路径，绝对路径优先
     */
    @Excel(name = "品牌logo",
            type = 2,
            width = 40,
            imageType = 1,
            savePath = "E:\\IdeaWorke-space\\bzmall\\bzmall-admin\\src\\main\\webapp\\img\\")
    private String logo;

    /**
     * 品牌描述
     */
    @Excel(name = "品牌描述")
    private String brandStory;

}

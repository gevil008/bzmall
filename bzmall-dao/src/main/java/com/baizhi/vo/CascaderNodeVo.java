package com.baizhi.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CascaderNodeVo implements Serializable {
    /**
     * 值 三级分类id
     */
    private Long value;
    /**
     * 三级分类名字
     */
    private String label;

    private List<CascaderNodeVo> children;
}

package com.baizhi.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ZTreeNode implements Serializable {
    private Long id;
    private String name;
    private List<ZTreeNode> children;
}
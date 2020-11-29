package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author caoyafei
 * @since 2020-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BzLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    private String username;

    private String logIp;

    private String logType;

    private String logContent;

    private String logMethod;

    private Integer logTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date logDate;

}

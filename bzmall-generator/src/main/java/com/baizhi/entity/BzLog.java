package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    private LocalDateTime logDate;


}

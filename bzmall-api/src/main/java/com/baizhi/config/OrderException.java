package com.baizhi.config;

/**
 * 自定义异常
 */
public class OrderException extends RuntimeException {
    public OrderException() {
        super("订单查询失败");
    }

    public OrderException(String message) {
        super(message);
    }
}

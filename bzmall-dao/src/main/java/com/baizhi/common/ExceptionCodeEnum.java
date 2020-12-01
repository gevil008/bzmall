package com.baizhi.common;

public enum ExceptionCodeEnum {
    /**
     * 定义枚举值
     * 枚举值 自定义（常量）
     * 可以使用 无参或者有参构造
     */
    ORDER_FAIL(1002,"订单失败"),
    VALID_FAIL(1001,"参数校验失败"),
    VERIFICATION_FAIL(1003,"验证码错误"),
    NOUSER_FAIL(1004,"用户不存在");

    /**
     * 枚举的参数
     */
    private int code;
    private String msg;

    /**
     * 构造方法
     */
    ExceptionCodeEnum() {
        this.code = 500;
        this.msg = "未知异常";
    }


    ExceptionCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

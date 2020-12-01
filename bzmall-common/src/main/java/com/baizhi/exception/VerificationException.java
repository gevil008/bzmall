package com.baizhi.exception;

public class VerificationException extends RuntimeException {
    public VerificationException() {
        super();
    }

    public VerificationException(String message) {
        super("验证码错误");
    }
}

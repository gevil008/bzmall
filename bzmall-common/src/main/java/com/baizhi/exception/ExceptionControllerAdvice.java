package com.baizhi.exception;

import com.baizhi.common.ExceptionCodeEnum;
import com.baizhi.vo.R;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ControllerAdvice 声明当前类为一个全局异常处理类
 *  在异常捕获类中可以进行视图跳转和Json响应
 * @RestControllerAdvice 声明当前类为一个全局异常处理类
 *  所有方法的都添加 @ResponseBody
 *  类中所有的方法的返回值都会被以json形式响应
 */
// @RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * @ExceptionHandler 捕获某个异常 赋值到方法的形参处
     * 方法形参必须为 被捕获异常或者其父类
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R getConstraintViolationException(ConstraintViolationException e){
        String message = e.getMessage();
        return R.error(
                ExceptionCodeEnum.VALID_FAIL.getCode(),
                ExceptionCodeEnum.VALID_FAIL.getMsg()).put("data",message);
    }

    @ExceptionHandler(BindException.class)
    public R getBindException(BindException e){
        // 获取异常信息
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map map = new HashMap();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        return R.error(
                ExceptionCodeEnum.ORDER_FAIL.getCode(),
                ExceptionCodeEnum.ORDER_FAIL.getMsg()).put("data",map);
    }

    /**
     * 自定义验证码异常处理
     */
    @ExceptionHandler(VerificationException.class)
    public R getVerificationException(VerificationException e){
        String message = e.getMessage();
        return R.error(
                ExceptionCodeEnum.VERIFICATION_FAIL.getCode(),
                ExceptionCodeEnum.VERIFICATION_FAIL.getMsg()).put("data",message);
    }

    /**
     * 用户名不存在
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public R getUsernameNotFoundException(UsernameNotFoundException e){
        String message = e.getMessage();
        return R.error(
                ExceptionCodeEnum.NOUSER_FAIL.getCode(),
                ExceptionCodeEnum.NOUSER_FAIL.getMsg()).put("data",message);
    }
}
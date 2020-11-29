package com.baizhi.log;

import com.baizhi.enums.LogTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解：加在自定义注解上的注解
 * @Target 声明当前自定义注解 可以加在什么地方 TYPE类上
 * @Retention 声明当前自定义注解的声明周期（作用方法） RUNITIME 一直生效
 *
 * SOURCE 代表当前注解 在java代码--->Class过程中生效 其他环节不生效
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    /**
     * 定义注解的属性
     */
    LogTypeEnum type() default LogTypeEnum.SELECT;

    String content();
}

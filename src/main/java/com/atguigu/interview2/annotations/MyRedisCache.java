package com.atguigu.interview2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther zzyy
 * @create 2024-05-16 19:28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRedisCache     //@EnableAspectJAutoProxy //启AOP自动代理
{
    //约等于键的前缀prefix,
    String keyPrefix();

    //SpringEL表达式，解析占位符对应的匹配value值
    String matchValue();
}
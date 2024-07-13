package com.atguigu.interview2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther zzyy
 * @create 2024-05-16 18:51
 */
@Target({ElementType.METHOD})//作用在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时生效
public @interface MethodExporter
{

}
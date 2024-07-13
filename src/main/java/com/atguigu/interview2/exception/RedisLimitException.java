package com.atguigu.interview2.exception;

/**
 * @auther zzyy
 * @create 2024-05-23 15:47
 */
/**
 * Redis限流自定义异常
 */
public class RedisLimitException extends RuntimeException
{
    public RedisLimitException(String msg)
    {
        super(msg);
    }
}

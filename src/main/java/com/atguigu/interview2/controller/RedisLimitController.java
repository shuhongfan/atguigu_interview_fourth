package com.atguigu.interview2.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.interview2.annotations.RedisLimitAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther zzyy
 * @create 2024-05-23 15:44
 */

@Slf4j
@RestController
public class RedisLimitController
{
    /**
     * Redis+Lua脚本+AOP+反射+自定义注解，打造我司内部基础架构限流组件
     * http://localhost:24618/redis/limit/test
     *
     * 在redis中，假定一秒钟只能有3次访问，超过3次报错
     * key = redisLimit
     * Value = permitsPerSecond设置的具体值
     * 过期时间 = expire设置的具体值,
     * permitsPerSecond = 3, expire = 10
     * 表示本次10秒内最多支持3次访问，到了3次后开启限流，过完本次10秒钟后才解封放开，可以重新访问
     */
    @GetMapping("/redis/limit/test")
    @RedisLimitAnnotation(key = "redisLimit", permitsPerSecond = 3, expire = 10, msg = "当前访问人数较多，请稍后再试，自定义提示！")
    public String redisLimit()
    {
        return "正常业务返回，订单流水："+ IdUtil.fastUUID();
    }
}


package com.atguigu.interview2.design.pattern.v1;

import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2024-03-07 17:19
 */
@Component
public class WahahaHandler implements HandlerStrategy
{
    @Override
    public void getCoca(String parameter)
    {
        System.out.println("我是娃哈哈可乐-only策略 "+parameter);
    }
}
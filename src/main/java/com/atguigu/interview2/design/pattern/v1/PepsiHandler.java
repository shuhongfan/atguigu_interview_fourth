package com.atguigu.interview2.design.pattern.v1;

import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2024-03-07 17:16
 */
@Component
public class PepsiHandler implements HandlerStrategy
{
    @Override
    public void getCoca(String parameter)
    {
        System.out.println("我是百事可乐-only策略 "+parameter);
    }
}

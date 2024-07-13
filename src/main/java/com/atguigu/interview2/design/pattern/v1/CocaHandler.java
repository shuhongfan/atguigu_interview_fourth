package com.atguigu.interview2.design.pattern.v1;

import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2024-03-07 17:17
 */
@Component
public class CocaHandler implements HandlerStrategy
{
    @Override
    public void getCoca(String parameter)
    {
        System.out.println("我是可口可乐-only策略 "+parameter);
    }
}

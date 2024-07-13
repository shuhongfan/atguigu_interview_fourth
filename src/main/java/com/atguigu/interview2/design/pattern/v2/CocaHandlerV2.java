package com.atguigu.interview2.design.pattern.v2;

import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2024-03-07 17:33
 */
@Component
public class CocaHandlerV2 implements HandlerStrategyFactory
{
    @Override
    public void getCoca(String parameter)
    {
        System.out.println("我是可口可乐-策略+工厂 "+parameter);
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
                               //K          //V
        Factory.register("Coca",this);
    }
}

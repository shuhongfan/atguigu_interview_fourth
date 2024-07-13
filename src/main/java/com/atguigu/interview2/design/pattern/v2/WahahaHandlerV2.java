package com.atguigu.interview2.design.pattern.v2;


import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2024-03-07 17:40
 */
@Component
public class WahahaHandlerV2 implements HandlerStrategyFactory
{
    @Override
    public void getCoca(String parameter)
    {
        System.out.println("我是娃哈哈可乐-策略+工厂 "+parameter);
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        Factory.register("Wahaha",this);
    }
}

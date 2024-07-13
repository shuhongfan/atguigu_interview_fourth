package com.atguigu.interview2.design.pattern.v3;

import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2024-03-08 22:27
 */
@Component
public class WahahaHandlerV3 extends AbstractColaHandler
{
    @Override
    public void getCoca(String parameter)
    {
        System.out.println("我是娃哈哈可乐-策略+工厂+模板 "+parameter);
    }

    @Override
    public String wahahaMethod(String name)
    {
        return "娃哈哈可乐WahahaHandlerV3独有";
    }

    @Override
    public String invokeCommon()
    {
        return "我是WahahaHandlerV3统一实现抽象父类的invokeCommon方法";
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        FactoryV3.register("Wahaha",this);
    }

}
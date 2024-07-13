package com.atguigu.interview2.design.pattern.v3;

import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2024-03-08 22:25
 */
@Component
public class PepsiHandlerV3 extends AbstractColaHandler
{
    @Override
    public void getCoca(String parameter)
    {
        System.out.println("我是百事可乐-策略+工厂+模板 "+parameter);
    }

    @Override
    public String pepsiMethod(String name)
    {
        return "百事可乐pepsiMethod独有";
    }

    @Override
    public String invokeCommon()
    {
        return "我是PepsiHandlerV3统一实现抽象父类的invokeCommon方法";
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        FactoryV3.register("Pepsi",this);
    }

}
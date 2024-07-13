package com.atguigu.interview2;

import com.atguigu.interview2.design.pattern.v2.Factory;
import com.atguigu.interview2.design.pattern.v2.HandlerStrategyFactory;
import com.atguigu.interview2.design.pattern.v3.AbstractColaHandler;
import com.atguigu.interview2.design.pattern.v3.FactoryV3;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Interview2ApplicationTests
{

    @Test
    void contextLoads()
    {
    }

    // V2策略+工厂设计模式配合
    @Test
    public void invokeStrategyFactory()
    {
        String parameter = "Coca";  //Pepsi //Coca

        HandlerStrategyFactory invokeStrategy = Factory.getInvokeStrategy(parameter);

        invokeStrategy.getCoca(parameter);
    }

    // V3策略+工厂+模板设计模式配合
    @Test
    public void invokeStrategyFactoryTemplate()
    {
        String parameter = "Wahaha";
        AbstractColaHandler invokeStrategy = FactoryV3.getInvokeStrategy(parameter);

        invokeStrategy.getCoca(parameter);

        System.out.println(invokeStrategy.invokeCommon());

        System.out.println(invokeStrategy.wahahaMethod(parameter));
    }
}
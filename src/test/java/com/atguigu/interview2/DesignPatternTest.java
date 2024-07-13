package com.atguigu.interview2;

import com.atguigu.interview2.design.pattern.v2.Factory;
import com.atguigu.interview2.design.pattern.v2.HandlerStrategyFactory;
import com.atguigu.interview2.design.pattern.v3.AbstractColaHandler;
import com.atguigu.interview2.design.pattern.v3.CocaHandlerV3;
import com.atguigu.interview2.design.pattern.v3.FactoryV3;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther zzyy
 * @create 2024-06-03 18:11
 */
@SpringBootTest
public class DesignPatternTest
{
    /**
     * 策略+工厂V2
     */
    @Test
    public void methodV2()
    {
        String parameter = "Coca";

        HandlerStrategyFactory invokeStrategy = Factory.getInvokeStrategy(parameter);

        invokeStrategy.getCoca(parameter);
    }

    /**
     * 策略+工厂+模板方法模式V3
     */
    @Test
    public void methodV3()
    {
        String parameter = "Coca";
        AbstractColaHandler handler = FactoryV3.getInvokeStrategy(parameter);

        handler.getCoca(parameter);

        System.out.println(handler.cocaMethod("可口可乐"));

    }
}

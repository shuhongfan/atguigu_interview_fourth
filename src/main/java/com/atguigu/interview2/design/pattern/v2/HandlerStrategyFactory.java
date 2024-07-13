package com.atguigu.interview2.design.pattern.v2;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @auther zzyy
 * @create 2024-03-07 17:32
 */
public interface HandlerStrategyFactory extends InitializingBean
{
    void getCoca(String parameter);

    //	void afterPropertiesSet() throws Exception;
}

// V2 策略+工厂+InitializingBean
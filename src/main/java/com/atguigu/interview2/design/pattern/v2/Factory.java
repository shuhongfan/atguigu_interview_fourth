package com.atguigu.interview2.design.pattern.v2;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther zzyy
 * @create 2024-03-07 17:28
 */
public class Factory
{
    private static Map<String, HandlerStrategyFactory> strategyMap = new ConcurrentHashMap<>();

    public static HandlerStrategyFactory getInvokeStrategy(String str)
    {
        return strategyMap.get(str);
    }

    public static void register(String str ,HandlerStrategyFactory handler)
    {
        System.out.println("str: "+str+"\t handler: "+handler);
        if (null == str || null == handler)
        {
            return;
        }
        strategyMap.put(str,handler);
    }
}

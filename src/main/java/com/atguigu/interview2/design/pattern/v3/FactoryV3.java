package com.atguigu.interview2.design.pattern.v3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther zzyy
 * @create 2024-03-08 22:19
 */
public class FactoryV3
{
    // 定义策略map缓存
    private static Map<String, AbstractColaHandler> strategyMap = new ConcurrentHashMap<>();


    public static AbstractColaHandler getInvokeStrategy(String str)
    {
        return strategyMap.get(str);
    }

    public static void register(String str,AbstractColaHandler handler)
    {
        System.out.println("str: "+str+"\t handler: "+handler);
        if (null == str || null == handler)
        {
            return;
        }
        strategyMap.put(str,handler);
    }
}

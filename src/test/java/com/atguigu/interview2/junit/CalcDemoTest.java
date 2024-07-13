package com.atguigu.interview2.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther zzyy
 * @create 2024-05-31 18:10
 */
class CalcDemoTest
{

    @Test
    void add()
    {
        CalcDemo calcDemo = new CalcDemo();
        //System.out.println(calcDemo.add(2, 2));//error,看到绿条不一定正确，不可以用sysout
        int retValue = calcDemo.add(2, 2);
        assertEquals(4,retValue);

        //看到绿条+使用了assert断言也不一定正确，为什么单元测试的多样性和覆盖率如此重要
        retValue = calcDemo.add(2, 7);
        assertEquals(9,retValue);

    }

    @Test
    void sub()
    {
        CalcDemo calcDemo = new CalcDemo();
        int retValue = calcDemo.sub(2, 2);
        assertEquals(0,retValue);
    }
}
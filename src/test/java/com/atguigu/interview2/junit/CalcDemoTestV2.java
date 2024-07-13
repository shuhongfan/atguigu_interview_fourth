package com.atguigu.interview2.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther zzyy
 * @create 2024-05-04 17:03
 */
class CalcDemoTestV2
{
    CalcDemo calcDemo = null;

    static StringBuffer stringBuffer = null;

    @BeforeAll
    static void m1()
    {
        stringBuffer = new StringBuffer("abc");
        System.out.println("===============: "+stringBuffer.length());
    }

    @AfterAll
    static void m2()
    {
        System.out.println("===============: "+stringBuffer.append(" ,end").toString());
    }



    @BeforeEach
    void setUp()
    {
        System.out.println("----come in BeforeEach");
        calcDemo = new CalcDemo();
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("----come in AfterEach");
        calcDemo = null;
    }

    @Test
    void add()
    {
        Assertions.assertEquals(5,calcDemo.add(1,4));
        assertEquals(5,calcDemo.add(2,3));
    }

    @Test
    void sub()
    {
        assertEquals(5,calcDemo.sub(10,5));
    }
}
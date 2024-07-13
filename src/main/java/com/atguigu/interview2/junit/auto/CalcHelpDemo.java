package com.atguigu.interview2.junit.auto;

/**
 * @auther zzyy
 * @create 2024-05-05 10:00
 */
public class CalcHelpDemo
{
    public int mul(int x ,int y)
    {
        return x * y;
    }

    @AtguiguTest
    public int div(int x ,int y)
    {
        return x / y;
    }
    @AtguiguTest
    public void thank(int x ,int y)
    {
        System.out.println("3ks，help me test bug");
    }
}

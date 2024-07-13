package com.atguigu.interview2.javase;

/**
 * @auther zzyy
 * @create 2024-05-02 17:15
 *
 * 阿里手册
 *
 * 【强制】所有整型包装类对象之间值的比较，全部使用equals方法比较。
 * 说明：对于Integer var = ? 在 -128至127之间的赋值，Integer 对象是在IntegerCache.cache 产生，会复用已有对
 * 象，这个区间内的Integer 值可以直接使用 == 进行判断，但是这个区间之外的所有数据，都会在堆上产生，并不会复
 * 用已有对象，这是一个大坑，推荐使用equals方法进行判断。
 */
public class Integer_BugDemo
{
    public static void main(String[] args)
    {

        //构造方法可行？
        //Integer i = new Integer(1200);
        //Integer i2 = Integer.valueOf(1200);


        Integer a = Integer.valueOf(600);
        Integer b = Integer.valueOf(600);
        int     c = 600;
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a == c);

        System.out.println("===================");

        Integer x = Integer.valueOf(99);
        Integer y = Integer.valueOf(99);
        System.out.println(x == y);
        System.out.println(x.equals(y));
    }
}

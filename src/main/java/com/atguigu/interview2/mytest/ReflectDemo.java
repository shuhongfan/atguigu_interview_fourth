package com.atguigu.interview2.mytest;

import com.atguigu.interview2.entities.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @auther zzyy
 * @create 2024-05-24 21:16
 */
public class ReflectDemo
{
    public static final int _1W = 10000;
    public static final int _1Y = 100000000;

    public static void m1_newObject()
    {
        Person person = new Person();
    }

    public static void m2_newObjectWithReflect()
    {
        try
        {
            Person person = Person.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Constructor<Person> personConstructor;
    static
    {
        try
        {
            personConstructor = Person.class.getDeclaredConstructor();
            personConstructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public static void m2_newObjectWithReflectV2()
    {
        try
        {
            Person person = personConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 5 * _1Y ; i++)
        {
            m1_newObject(); //2
            //m2_newObjectWithReflect();//11377
            //m2_newObjectWithReflectV2(); //451
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒");
    }
}


//design.pattern
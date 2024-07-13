package com.atguigu.interview2.javase;

import java.util.HashSet;
import java.util.Set;
import com.atguigu.interview2.entities.Person;

/**
 * @auther zzyy
 * @create 2024-05-01 16:11
 *   == 和 equals
 *	1 比较范围
 *	  1.1 == 既可以比较基本类型也可以比较引用类型
 *	  1.2 equals
 *	  只能比较引用类型，equals(Object obj)
 *
 *
 *	2 比较规则
 *	  	==对于基本类型值是否相等，对于引用类型内存地址是否相等
 *	  equals比较规则，看是否被重写过。
 *	  没有被重写，出厂默认就是==
 *	  如果被重写，具体看实现方法
 */
public class TestEquals
{
    public static void main(String[] args)
    {
        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        Set<String> set01 = new HashSet<>();
        set01.add(s1);
        set01.add(s2);
        System.out.println(set01.size());
        System.out.println(s1.hashCode()+"\t"+s2.hashCode());
        System.out.println("================================");

        Person p1 = new Person("abc");
        Person p2 = new Person("abc");
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));
        Set<Person> set02 = new HashSet<>();
        set02.add(p1);
        set02.add(p2);
        System.out.println(set02.size());
        System.out.println(p1.hashCode()+"\t"+p2.hashCode());
        System.out.println("================================");
        System.out.println();
    }
}
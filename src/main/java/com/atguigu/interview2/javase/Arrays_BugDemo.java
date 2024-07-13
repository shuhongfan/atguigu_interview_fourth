package com.atguigu.interview2.javase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther zzyy
 * @create 2024-05-08 17:59
 */
public class Arrays_BugDemo
{
    public static void main(String[] args)
    {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));


        list.add(6);

        list.forEach(System.out::println);
    }
}

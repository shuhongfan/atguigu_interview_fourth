package com.atguigu.interview2.javase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @auther zzyy
 * @create 2024-05-09 10:18
 */
public class IteratorRemoveDemo
{
    public static void main(String[] args)
    {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);

        Iterator<Integer> iterator = list.iterator();

        while(iterator.hasNext())
        {
            Integer value = iterator.next();
            if(value == 12)
            {
                //list.remove(value);//Exception in thread "main" java.util.ConcurrentModificationException
                iterator.remove();
            }
        }
        list.forEach(v -> System.out.println(v));
    }
}

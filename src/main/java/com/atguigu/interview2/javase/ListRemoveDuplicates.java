package com.atguigu.interview2.javase;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @auther zzyy
 * @create 2024-05-01 14:59
 *
 */
public class ListRemoveDuplicates
{
    /**
     * list去除重复元素你能想到几个方法，校招题
     * 有些面试官不让你用API，要求纯手写
     *
     * 如果你能找到更多更好方法，请给阳哥邮件:  zzyybs@126.com
     *
     * @param args
     */
    public static void main(String[] args)
    {
        //m1();
        //m2();
        //m3();
        //m4();
        m5();
    }


    /**
     * 双for循环对比，通过下标获得值，外层循环和内层循环对比，值相同去重复
     */
    private static void m5()
    {
        List<Integer> initList = Arrays.asList(70,70,-1,5,3,3,4,4,4,4,99);
        List<Integer> srcList = new ArrayList<>(initList);
        List<Integer> newList = new ArrayList<>(initList);

        for (int i = 0; i < newList.size()-1; i++)
        {
            for (int j = newList.size()-1; j > i ; j--)
            {
                if(newList.get(j).equals(newList.get(i)))
                {
                    newList.remove(j);
                }
            }
        }
        newList.forEach(s -> System.out.print(s+" "));
    }

    /**
     * 循环坐标去除重复,类似双指针，按照值找到下标，
     * 如果下标不同，表示有重复需要删除
     */
    private static void m4()
    {
        List<Integer> initList = Arrays.asList(70,70,-1,5,3,3,4,4,4,4,99);
        List<Integer> srcList = new ArrayList<>(initList);
        List<Integer> newList = new ArrayList<>(initList);

        for (Integer element : srcList)
        {
            if(newList.indexOf(element) != newList.lastIndexOf(element))
            {
                newList.remove(newList.lastIndexOf(element));
            }
        }
        newList.forEach(s -> System.out.print(s+" "));

    }

    /**
     * Stream流式计算
     */
    private static void m3()
    {
        List<Integer> initList = Arrays.asList(70,70,-1,5,3,3,4,4,4,4,99);
        List<Integer> srcList = new ArrayList<>(initList);
        List<Integer> newList = new ArrayList<>();

        newList = srcList.stream().distinct().collect(Collectors.toList());

        newList.forEach(s -> System.out.print(s+" "));
    }

    /**
     * HashSet或者LinkedHashSet去重复
     */
    private static void m2()
    {
        List<Integer> srcList = Arrays.asList(70,70,-1,5,3,3,4,4,4,4,99);
        List<Integer> newList = new ArrayList<>(new HashSet<>(srcList));
        newList.forEach(s -> System.out.print(s+" "));

        System.out.println();
        System.out.println();

        newList = new ArrayList<>(new LinkedHashSet<>(srcList));
        newList.forEach(s -> System.out.print(s+" "));

    }



    /**
     * for循环遍历判断是否含有，没有就新增到newList里
     */
    private static void m1()
    {
        List<Integer> initList = Arrays.asList(70,70,-1,5,3,3,4,4,4,4,99);
        List<Integer> srcList = new ArrayList<>(initList);
        List<Integer> newList = new ArrayList<>();

        for (int i = 0; i < srcList.size(); i++) {
            Integer tmpValue = srcList.get(i);
            if(!newList.contains(tmpValue))
            {
                newList.add(tmpValue);
            }
        }
        System.out.println(newList);
    }

}

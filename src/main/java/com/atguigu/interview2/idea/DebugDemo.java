package com.atguigu.interview2.idea;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @auther zzyy
 * @create 2024-05-30 11:07
 */
public class DebugDemo
{
    public static void helloDebug()
    {
        Map<Integer,String> map = new HashMap<>();

        map.put(1,"a");
        map.put(2,"b");
        baseEmbed();
        map.put(3,"c");
        map.put(4,"d");

        map.forEach((k,v) -> {
            System.out.println(k+"\t"+v);
        });
    }

    public static void baseEmbed()
    {
        System.out.println("baseEmbed()----1111");
        System.out.println("baseEmbed()----2222");
        System.out.println("baseEmbed()----3333");
        System.out.println("baseEmbed()----4444");
    }

    private static void myStreamChain()
    {
        List<Integer> list = Stream.of(1,2,3,4,5,6).filter(f -> f>3).map(m -> m * 2).collect(Collectors.toList());
    }

    private static void forceReturnAndStop()
    {
        System.out.println("---mysql-01");
        baseEmbed();

        System.out.println("---redis-02");
        System.out.println("---RabbitMQ-03");
        System.out.println("---hashCode: "+new Object().hashCode());
    }

    //Field BreakPoint,在变量Field上面打断点，默认眼睛图标
    private static int x;
    public static void setFieldWatchpoint()
    {
        x = 55;
        setFieldWatchpoint2();
    }
    public static void setFieldWatchpoint2()
    {
        x = 77;
    }

    public static void main(String[] args)
    {
        //1 第一组基础界面
        // 光标回到当前断点行+ 步过+步入+强制步入+步出+运行到光标位置
        //helloDebug();

        //2 Trace Current Stream Chain
        //myStreamChain();


        //3 forceReturn，少用stop，减少一次废数据
        //forceReturnAndStop();

        //4 四大断点Line Breakpoint+Field Watchpoint+Method Breakpoint+Exception Breakpoint
        //4.1 Line Breakpoint，大家都懂，略......

        //4.2 Field Watchpoint，在变量Field上面打断点，默认眼睛图标
        //setFieldWatchpoint();

        //4.3 Method Breakpoint，在接口内定义的方法上面打断点，默认菱形图标
        /*Phone phone = new Phone();
        phone.insert();
        Computer computer = new Computer();
        computer.insert();*/



        //4.4 Exception BreakPoint
        //4.4.1 新增异常断点,点击View Breakpoints...
        //4.4.2 选择异常类型
        //4.4.3 选择hit和log
        //4.4.4 运行含有null异常的代码，自动闪电定位，不用人工打断点
        Integer i = null;
        System.out.println(i.intValue());

    }
}

interface USB
{
    public void insert();
}

class Phone implements USB
{
    @Override
    public void insert()
    {
        System.out.println("----手机支持USB");
    }
}

class Computer implements USB
{
    @Override
    public void insert()
    {
        System.out.println("----电脑支持USB");
    }
}
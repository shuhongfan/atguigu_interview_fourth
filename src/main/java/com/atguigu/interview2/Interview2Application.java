package com.atguigu.interview2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.atguigu.interview2.mapper") //import tk.mybatis.spring.annotation.MapperScan;
public class Interview2Application
{

    // 第一学历统招本科，愿意跟着阳哥学技术，需要内推或高阶java资料学习的同学发邮件zzyybs@126.com

    /*@Resource
    private ThreadPoolTaskExecutor threadPool;

    @PostConstruct
    public void getThreadPoolConfig()
    {
        System.out.println("*******测试threadPool getCorePoolSize: "+threadPool.getCorePoolSize());
        System.out.println("*******测试threadPool getMaxPoolSize: "+threadPool.getMaxPoolSize());
        System.out.println("*******测试threadPool getQueueCapacity: "+threadPool.getQueueCapacity());
        System.out.println("*******测试threadPool getKeepAliveSeconds: "+threadPool.getKeepAliveSeconds());
    }*/

    public static void main(String[] args)
    {
        SpringApplication.run(Interview2Application.class, args);
    }

}

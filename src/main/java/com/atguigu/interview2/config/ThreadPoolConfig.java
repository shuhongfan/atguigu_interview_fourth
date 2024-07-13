package com.atguigu.interview2.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @auther zzyy
 * @create 2024-05-08 16:52
 *
 * ThreadPoolTaskExecutor 是 Spring 提供的一个方便的线程池实现，用于异步执行任务或处理并发请求。
 *
 * 在使用 ThreadPoolTaskExecutor 作为 Spring Bean 注册到容器中后，
 * Spring 会负责在应用程序关闭时自动关闭所有注册的线程池，所以不需要手动关闭。
 *
 * 这样不仅可以确保线程池中的线程正确地停止，还可以防止资源泄露和潜在的并发问题。
 */
@Configuration
public class ThreadPoolConfig
{
    /*
    @Value("${thread.pool.corePoolSize}")
    private String corePoolSize;

    @Value("${thread.pool.maxPoolSize}")
    private String maxPoolSize;

    @Value("${thread.pool.queueCapacity}")
    private String queueCapacity;

    @Value("${thread.pool.keepAliveSeconds}")
    private String keepAliveSeconds;
    */

    //线程池配置
    @Resource
    private ThreadPoolProperties threadPoolProperties;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor()
    {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();

        // 核心线程池大小
        threadPool.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        // 最大可创建的线程数
        threadPool.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        // 等待队列最大长度
        threadPool.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        // 线程池维护线程所允许的空闲时间
        threadPool.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        //异步方法内部线程名称
        threadPool.setThreadNamePrefix("spring默认线程池-");
        // 线程池对拒绝任务(无线程可用)的处理策略
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 任务都完成再关闭线程池
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 任务初始化
        threadPool.initialize();

        return threadPool;
    }

}

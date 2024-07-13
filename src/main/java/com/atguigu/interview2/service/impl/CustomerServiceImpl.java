package com.atguigu.interview2.service.impl;

import com.atguigu.interview2.entities.CustomerMixInfo;
import com.atguigu.interview2.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2024-05-08 10:31
 */
@Service
public class CustomerServiceImpl implements CustomerService
{

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 15, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(30));

    @Override
    public CustomerMixInfo findCustomer()
    {
        CustomerMixInfo customerMixInfo = new CustomerMixInfo();

        long startTime = System.currentTimeMillis();

        customerMixInfo.setCid(1024L);
        customerMixInfo.setCname(this.getCustomerName());
        customerMixInfo.setScore(this.getScore());
        customerMixInfo.setOrderInfo(this.getOrderInfo());

        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒");

        return customerMixInfo;
    }

    @Override
    public CustomerMixInfo findCustomerByCompletableFuture()
    {
        CustomerMixInfo customerMixInfo = new CustomerMixInfo();
        customerMixInfo.setCid(1024L);

        long startTime = System.currentTimeMillis();

        CompletableFuture<Void> customerInfoFuture = CompletableFuture.runAsync(() -> {
            customerMixInfo.setCname(this.getCustomerName());
        },threadPoolExecutor);

        CompletableFuture<Void> scoreFuture = CompletableFuture.runAsync(() -> {
            customerMixInfo.setScore(this.getScore());
        },threadPoolExecutor);

        CompletableFuture<Void> orderInfoFuture = CompletableFuture.runAsync(() -> {
            customerMixInfo.setOrderInfo(this.getOrderInfo());
        },threadPoolExecutor);

        /**
         * public static CompletableFuture<Void> allOf​(CompletableFuture<?>... cfs)
         * 返回在所有给定的CompletableFutures完成时完成的新CompletableFuture。
         * 如果任何给定的CompletableFutures异常完成，则返回的CompletableFuture也会这样做，
         * 并且CompletionException将此异常作为其原因。 否则，给定的CompletableFutures的结果（如果有的话）
         * 不会反映在返回的CompletableFuture中，
         * 但可以通过单独检查它们来获得。 如果未提供CompletableFutures，则返回CompletableFuture，其值为null 。
         * 此方法的应用之一是在继续执行程序之前等待完成一组独立的CompletableFutures，
         * 如： CompletableFuture.allOf(c1, c2, c3).join(); 。
         *
         * 参数
         * cfs - CompletableFutures
         * 结果
         * 在所有给定的CompletableFutures完成时完成的新CompletableFuture
         * 异常
         * NullPointerException - 如果数组或其任何元素是 null
         */
        CompletableFuture.allOf(customerInfoFuture, scoreFuture, orderInfoFuture).join();

        long endTime = System.currentTimeMillis();
        System.out.println("----使用CompletableFuture测试案例,costTime: "+(endTime - startTime) +" 毫秒");

        return customerMixInfo;
    }


    //================模拟3个不同的微服务系统
    public String getCustomerName()
    {
        try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
        return "张三";
    }
    public Long getScore()
    {
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        return 100L;
    }
    public String getOrderInfo()
    {
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        return "OrderSerial001-华为P70手机";
    }
}

package com.atguigu.interview2.service.impl;

import com.atguigu.interview2.service.CouponServiceV2;
import com.atguigu.interview2.utils.TaskBatchSendUtils;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther zzyy
 * @create 2024-05-08 17:06
 */
@Service
public class CouponServiceImplV2 implements CouponServiceV2
{
    //下发优惠卷数量
    public  static final Integer COUPON_NUMBER = 50;

    @Resource
    private ThreadPoolTaskExecutor threadPool;

    @SneakyThrows
    @Override
    public void batchTaskActionV2()
    {
        //1 模拟要下发的50条优惠卷，上游系统给我们的下发优惠卷源头
        List<String> coupons = getCoupons();

        long startTime = System.currentTimeMillis();

        //2 调用工具类批处理任务,这些优惠卷coupons，放入线程池threadPool，做什么业务disposeTask下发
        TaskBatchSendUtils.send(coupons,threadPool,TaskBatchSendUtils::disposeTaskV2);

        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒");

    }

    private static List<String> getCoupons()
    {
        List<String> coupons = new ArrayList<>(COUPON_NUMBER);
        for (int i = 1; i <= COUPON_NUMBER; i++)
        {
            coupons.add("优惠卷--"+i);
        }
        return coupons;
    }
}


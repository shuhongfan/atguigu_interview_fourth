package com.atguigu.interview2.juc;


import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

//资源类，保时米SU7轿跑,本门店有3个销售(3个线程)
class SU7
{
    @Getter
    private int saleTotal;//本门店总体销售额
    public synchronized void saleTotal()
    {
        saleTotal++;
    }

    //ThreadLocal 求3个销售的各自独立的个体销售额，不参加总和计算
    /*ThreadLocal<Integer> salePersonal = new ThreadLocal<>(){
        @Override
        protected Integer initialValue()
        {
            return 0;
        }
    };*/

    ThreadLocal<Integer> salePersonal = ThreadLocal.withInitial(() -> 0);
    public void salePersonal()
    {
        salePersonal.set(1 + salePersonal.get());
    }

}

/**
 * ThreadLocal案例演示
 *
 * 第一次需求：
 *  3个销售卖保时米SU7，求3个销售本团队总体销售额
 *
 * 第二次需求：
 * 3个销售卖保时米SU7，求3个销售的各自独立的个体销售额，不参加总和计算，
 *   希望各自分灶吃饭，各凭销售本事提成，按照出单数各自统计，
 *   每个销售都有自己的销售额指标，自己专属自己的，不和别人掺和
 *
 * @auther zzyy
 * @create 2024-05-06 11:16
 */
public class ThreadLocalDemoV1
{
    public static void main(String[] args) throws InterruptedException
    {
        // 线程   操作  资源类
        SU7 su7 = new SU7();
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 1; i <=3; i++)
        {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= new Random().nextInt(3)+1 ; j++) {
                        su7.saleTotal();//本门店销售总和统计全部加
                        su7.salePersonal();//各个销售独立的销售额，之和自己有关
                    }
                    System.out.println(Thread.currentThread().getName()+"\t"+" 号销售卖出: "+su7.salePersonal.get());
                } finally {
                    countDownLatch.countDown();
                    //su7.salePersonal.remove(); 本案例故意不加，留在下一讲
                }
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t"+"销售总额: "+su7.getSaleTotal());
    }
}

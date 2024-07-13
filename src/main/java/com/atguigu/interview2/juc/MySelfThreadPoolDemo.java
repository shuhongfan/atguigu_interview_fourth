package com.atguigu.interview2.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @auther zzyy
 * @create 2024-06-02 17:25
 */
@Slf4j
public class MySelfThreadPoolDemo
{
    public static void main(String[] args)
    {
        ExecutorService threadPool = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors() * 2,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100)) {
            @Override
            protected void afterExecute(Runnable runnable, Throwable throwable)
            {
                //execute运行
                if (throwable != null)
                {
                    log.error(throwable.getMessage(), throwable);
                }
                //submit运行
                if (throwable == null && runnable instanceof Future<?>)
                {
                    try
                    {
                        Future<?> future = (Future<?>) runnable;
                        if (future.isDone())
                        {
                            future.get();
                        }
                    } catch (CancellationException ce){
                        throwable = ce;
                        ce.printStackTrace();
                    } catch (ExecutionException ee){
                        throwable = ee.getCause();
                        ee.printStackTrace();
                    } catch (InterruptedException ie){
                        ie.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        try
        {
            //业务逻辑编写，将需求提交给池中工作线程处理

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            finalOK_shutdownAndAwaitTermination(threadPool);
        }
    }


    /**
     * 参考官网使用,线程池优雅关停，可以自己写个工具类单独调用
     * @param threadPool
     */
    public static void finalOK_shutdownAndAwaitTermination(ExecutorService threadPool)
    {
        if (threadPool != null && !threadPool.isShutdown())
        {
            threadPool.shutdown();
            try
            {
                if (!threadPool.awaitTermination(120, TimeUnit.SECONDS))
                {
                    threadPool.shutdownNow();

                    if (!threadPool.awaitTermination(120, TimeUnit.SECONDS))
                    {
                        System.out.println("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                threadPool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

}

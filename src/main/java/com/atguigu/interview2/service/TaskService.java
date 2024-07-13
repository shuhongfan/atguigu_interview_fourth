package com.atguigu.interview2.service;

import cn.hutool.core.util.IdUtil;
import com.atguigu.interview2.entities.Constants;
import com.atguigu.interview2.entities.Content;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.RandomUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther zzyy
 * @create 2024-05-25 18:03
 */
@Service
@Slf4j
public class TaskService {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     *模拟直播间的数据
     */
    @PostConstruct
    public void init(){
        log.info("启动初始化,淘宝直播弹幕case开始 ..........");

        System.out.println();

        //1 微服务启动一个线程，模拟直播间各个观众发言
        new Thread(() ->
        {
            AtomicInteger atomicInteger = new AtomicInteger();

            while (true)
            {
                //2 模拟观众各种发言，5秒一批数据，自己模拟造一批发言数据到100后break
                if(atomicInteger.get() == 100)
                {
                    break;
                }
                //3 模拟直播100房间号 的弹幕数据，拼接redis的Key   room:100
                String  key= Constants.ROOM_KEY+100;
                Random rand = new Random();

                for(int i=1;i<=5;i++){
                    Content content = new Content();
                    content.setId(IdUtil.createSnowflake(1, 1).nextId());

                    int id= rand.nextInt(1000) + 1;
                    content.setUserId(id);

                    int temp= rand.nextInt(100) + 1;
                    content.setContent("发表言论: "+temp+"\t"+RandomUtil.randomString(temp));

                    long time=System.currentTimeMillis()/1000;
                    //4 对应的redis命令 zadd room:100 time content
                    /**
                     * 4.1 redis的原生命令
                     * 	ZADD key score1 member1 [score2 member2] [score3 member3]
                     * 向有序集合添加一个或多个成员，或者更新已存在成员的分数
                     *
                     * 4.2 redisTemplate操作Zset的API
                     * Boolean add(K key, V value, double score);
                     */
                    this.redisTemplate.opsForZSet().add(key,content,time);

                    log.info("模拟直播间100房间号的发言弹幕数据={}",content);
                }
                //TODO 在分布式系统中，建议用xxl-job来实现定时,此处阳哥为了直播方便讲解，简单模拟
                try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

                //模拟观众各种发言，5秒一批数据,到100自动退出break
                atomicInteger.getAndIncrement();

                System.out.println("-------每间隔5秒钟，拉取一次最新聊天记录");
            }
        },"init_Live_Data").start();
    }
}

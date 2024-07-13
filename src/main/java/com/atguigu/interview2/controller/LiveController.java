package com.atguigu.interview2.controller;

import com.atguigu.interview2.entities.Constants;
import com.atguigu.interview2.entities.Content;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @auther zzyy
 * @create 2024-05-25 18:04
 */

@RestController
@Slf4j
public class LiveController
{
    @Resource
    private RedisTemplate redisTemplate;

    /**
     *某个用户(userId=12)第一次进入房间,返回最新的前5条弹幕
     * http://localhost:24618/goRoom?roomId=100&userId=12
     */
    @GetMapping(value = "/goRoom")
    public List<Content> goRoom(Integer roomId, Integer userId){

        List<Content> list= new ArrayList<>();

        String  key= Constants.ROOM_KEY+roomId;
        //进入房间,返回最新的前5条弹幕
        //对应redis命令，ZREVRANGE room:100 0 4 WITHSCORES
        Set<ZSetOperations.TypedTuple<Content>> rang= this.redisTemplate.opsForZSet().reverseRangeWithScores(key,0,4);
        for (ZSetOperations.TypedTuple<Content> obj:rang)
        {
            list.add(obj.getValue());
            log.info("首次进房间取最新前5条弹幕content={},score={}",obj.getValue(),obj.getScore().longValue());
        }

        //user:room:time:12   12就是UserID观众ID
        String userkey=Constants.ROOM_USER_TIME_KEY+userId;
        //把当前的时间T,保持到redis，供下次拉取用，看过的就不再推送
        Long now=System.currentTimeMillis()/1000;
        this.redisTemplate.opsForValue().set(userkey,now);

        return list;
    }

    /**
     *登录房间后持续观看，定时任务或者xxl-job 客户端间隔5秒钟来拉取数据
     * http://localhost:24618/commentList?roomId=100&userId=12
     */
    @GetMapping(value = "/commentList")
    public List<Content>  commentList(Integer roomId,Integer userId){
        List<Content> list= new ArrayList<>();

        String key= Constants.ROOM_KEY+roomId;
        String userkey=Constants.ROOM_USER_TIME_KEY+userId;

        long now=System.currentTimeMillis()/1000;
        //拿取上次的读取时间
        Long ago=Long.parseLong(this.redisTemplate.opsForValue().get(userkey).toString());
        log.info("查找时间范围：{}  {}",ago,now);
        //获取上次到现在的数据 ZREVRANGE room:100 0 4 WITHSCORES
        Set<ZSetOperations.TypedTuple<Content>> rang= this.redisTemplate.opsForZSet().rangeByScoreWithScores(key,ago,now);
        for (ZSetOperations.TypedTuple<Content> obj:rang)
        {
            list.add(obj.getValue());
            log.info("持续观看直播content={},score={}",obj.getValue(),obj.getScore().longValue());
        }
        //把当前的时间Time,保持到redis，供下次拉取用
        now=System.currentTimeMillis()/1000;
        this.redisTemplate.opsForValue().set(userkey,now);

        return list;
    }
}




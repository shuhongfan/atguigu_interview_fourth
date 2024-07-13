package com.atguigu.interview2.service.impl;

import com.atguigu.interview2.annotations.MyRedisCache;
import com.atguigu.interview2.entities.User;
import com.atguigu.interview2.mapper.UserMapper;
import com.atguigu.interview2.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @auther zzyy
 * @create 2024-02-17 16:15
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService
{
    //==========第一组，only mysql==========
/*    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(User user)
    {
        return userMapper.insertSelective(user);
    }

    @Override
    public User getUserById(Integer id)
    {
        return userMapper.selectByPrimaryKey(id);
    }*/




    //==========第二组，redis+mysql==========
    /*public static final String CACHE_KEY_USER = "user:";
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public int addUser(User user)
    {
        log.info("插入之前user:{}",user);
        //1 先往mysql插入记录
        int retValue = userMapper.insertSelective(user);
        log.info("插入之后user:{}",user);
        log.info("=================================");

        if(retValue > 0)
        {
            //2 插入成功到数据库里面，重新捞出新数据出来，放入缓存
            user = this.userMapper.selectByPrimaryKey(user.getId());
            //3 拼装缓存key
            String key = CACHE_KEY_USER+user.getId();
            //4 往mysql里面插入成功随后再从mysql查询出来，再插入redis
            redisTemplate.opsForValue().set(key,user);
        }
        return retValue;
    }


    @Override
    public User getUserById(Integer id)
    {
        User user = null;
        //1 拼装缓存key
        String key=CACHE_KEY_USER+id;
        //2 查询redis
        user = (User) redisTemplate.opsForValue().get(key);
        //3 redis无，进一步查询mysql
        if(user == null)
        {
            //3.1 从mysql查出来user
            user = this.userMapper.selectByPrimaryKey(id);
            //3.2 mysql有，redis无
            if (user != null)
            {
                //3.3 把mysql捞到的数据写入redis，方便下次查询能redis命中。
                redisTemplate.opsForValue().set(key,user);
            }
        }
        //4 redis有，直接返回
        return user;
    }*/



    //==========第三组，@MyRedisCache+mysql==========
    public static final String CACHE_KEY_USER = "user:";
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public int addUser(User user)
    {
        log.info("插入之前user:{}",user);
        int retValue = userMapper.insertSelective(user);
        log.info("插入之后user:{}",user);
        log.info("=================================");

        if(retValue > 0)
        {
            //到数据库里面，重新捞出新数据出来，做缓存
            user=this.userMapper.selectByPrimaryKey(user.getId());
            //缓存key
            String key=CACHE_KEY_USER+user.getId();
            //往mysql里面插入成功随后再从mysql查询出来，再插入redis
            redisTemplate.opsForValue().set(key,user);
        }
        return retValue;
    }

    /**
     * 会将返回值存进redis里，key生成规则需要程序员用SpEL表达式自己指定，value就是程序从mysql查出并返回的user
     * redis的key 等于  keyPrefix:matchValue
     */
    @Override
    @MyRedisCache(keyPrefix = "user",matchValue = "#id")
    public User getUserById(Integer id)
    {
        return userMapper.selectByPrimaryKey(id);
    }
}



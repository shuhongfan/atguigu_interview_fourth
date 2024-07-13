package com.atguigu.interview2.entities;

/**
 * @auther zzyy
 * @create 2024-05-25 18:01
 */
public class Constants {

    //room:100 尚硅谷直播间 即room:100是redis key
    public static final String ROOM_KEY = "room:";

    //用户读取点播数据的时间点,某个观众什么时间戳进入到了直播间
    public static final String ROOM_USER_TIME_KEY = "user:room:time:";//user:room:time:12   12就是UserID观众ID

}

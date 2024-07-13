package com.atguigu.interview2.service;


import com.atguigu.interview2.entities.User;

/**
 * @auther zzyy
 * @create 2024-02-17 16:14
 */
public interface UserService
{
    public int addUser(User user);

    public User getUserById(Integer id);
}

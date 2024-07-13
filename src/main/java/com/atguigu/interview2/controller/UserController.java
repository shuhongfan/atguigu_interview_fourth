package com.atguigu.interview2.controller;

import com.atguigu.interview2.Interview2Application;
import com.atguigu.interview2.entities.User;
import com.atguigu.interview2.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @auther zzyy
 * @create 2024-02-17 16:16
 */
@RestController
public class UserController
{
    @Resource
    private UserService userService;

    @PostMapping(value = "/user/add")
    public int addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable("id") Integer id)
    {
        return userService.getUserById(id);
    }


}



/*

###
POST http://localhost:24618/user/add
Content-Type: application/json

{
  "username": "尚硅谷05",
  "password": "13911111115",
  "sex": "1"
}
*/

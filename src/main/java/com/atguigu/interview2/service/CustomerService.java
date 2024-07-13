package com.atguigu.interview2.service;

import com.atguigu.interview2.entities.CustomerMixInfo;

/**
 * @auther zzyy
 * @create 2024-05-08 10:30
 */
public interface CustomerService
{
    public CustomerMixInfo findCustomer();

    public CustomerMixInfo findCustomerByCompletableFuture ();
}

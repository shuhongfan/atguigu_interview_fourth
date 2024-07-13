package com.atguigu.interview2.controller;

import com.atguigu.interview2.service.CouponService;
import com.atguigu.interview2.service.CouponServiceV2;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther zzyy
 * @create 2024-05-08 17:04
 */

@RestController
@Slf4j
public class CouponController
{
    @Resource
    private CouponService couponService;

    //http://localhost:24618/coupon/sendv1
    @GetMapping(value = "/coupon/sendv1")
    public void sendv1()
    {
        couponService.batchTaskAction();
    }

    @Resource
    private CouponServiceV2 couponServiceV2;
    //http://localhost:24618/coupon/sendv2
    @GetMapping(value = "/coupon/sendv2")
    public void sendv2()
    {
        couponServiceV2.batchTaskActionV2();
        // 第一学历统招本科，愿意跟着阳哥学技术，需要内推或高阶java资料学习的同学发邮件zzyybs@126.com
    }
}

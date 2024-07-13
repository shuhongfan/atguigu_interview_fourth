package com.atguigu.interview2.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.interview2.aopreview.PayService;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther zzyy
 * @create 2024-05-16 18:16
 */
@RestController
public class PayAopReviewController
{
    @Resource
    private PayService payService;

    @GetMapping(value = "/pay/aop")
    public String pay()
    {
        System.out.println("SpringVersion: "+ SpringVersion.getVersion()+"\t"+ "SpringBootVersion: "+ SpringBootVersion.getVersion());

        payService.pay();


        return IdUtil.simpleUUID();
    }
}

package com.atguigu.interview2.aopreview;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;

/**
 * @auther zzyy
 * @create 2024-05-16 18:03
 */
@Service
public class PayServiceImpl implements PayService
{
    @Override
    public void pay()
    {
        System.out.println("PayServiceImpl微信支付业务逻辑："+ IdUtil.simpleUUID());

        int age=10/0;
    }
}

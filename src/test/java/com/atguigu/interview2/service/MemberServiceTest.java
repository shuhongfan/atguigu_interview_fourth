package com.atguigu.interview2.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @auther zzyy
 * @create 2024-05-31 17:08
 *
 * Mock注入
 * 实现原理：使用打桩(Stub)技术动态替换原来的程序
 * 直接跑Java代码，验证逻辑，不太关心真实数据落盘，不需要启动微服务和连接真实数据库
 * 模拟一切操作步骤，逻辑通过，不执行SQL，可以指定返回任意值。优点如下：
 * 1 完全脱离数据库/MQ等，避免废数据产生
 * 2 只针对一个小方法逻辑，不启动其它避免其它因素产生的干扰。
 *
 */
@SpringBootTest
class MemberServiceTest //类似MemberController
{
    //==========第一组==========
    /*@Resource //真实调用，落盘mysql-MQ=redis。。。。测试条件充分的情况下
    private MemberService memberService1;

    @Test
    void m1()
    {
        String result = memberService1.add(2);
        assertEquals("ok",result);

        System.out.println("----m1 over");
    }*/




    //==========第二组==========
    //如果没有指定规则的话，统统返回默认值，对象为null，数字为零;制定了mock规则就按照规则走
    //Mock注入实现原理：使用打桩(Stub)技术动态替换原来的程序
    /*@MockBean
    private MemberService memberService2;

    @Test
    void m2_NotMockRule()
    {
        String result = memberService2.add(2);
        assertEquals("ok",result);

        System.out.println("----m2_NotMockRule over");
    }


    @Test
    void m2_WithMockRule()
    {
        when(memberService2.add(3)).thenReturn("ok");//不真的进入数据库/MQ，不落盘，改变return

        String result = memberService2.add(3);
        assertEquals("ok",result);

        System.out.println("----m2_WithMockRule over");
    }*/





    //==========第三组==========
    //@Resource //真实调用
    //@MockBean//如果没有指定规则的话，统统返回默认值，对象为null，数字为零;制定了mock规则就按照规则走
    @SpyBean //有规则按照规则走，没有规则走真实
    private MemberService memberService3;
    @Test
    void m3()
    {
        when(memberService3.add(2)).thenReturn("ok");
        String result = memberService3.add(2);
        System.out.println("----add result:  "+result);
        assertEquals("ok",result);

        int result2 = memberService3.del(3);
        System.out.println("----del result2:  "+result2);
        assertEquals(3,result2);

//跨部门调用，不是写代码，累的是心，协调工作。    zzyybs@126.com
        System.out.println("----over");
    }
}
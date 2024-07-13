package com.atguigu.interview2.design.pattern.v3;

import org.springframework.beans.factory.InitializingBean;

/**
 * @auther zzyy
 * @create 2024-03-08 22:02
 */
public abstract class AbstractColaHandler implements InitializingBean
{
    public void getCoca(String parameter)
    {
        throw new UnsupportedOperationException();
    }

    //模板方法1，按照业务名字可乐自己单独实现
    public String cocaMethod(String name) {
        throw new UnsupportedOperationException();
    }
    //模板方法2，按照业务百事可乐自己单独实现
    public String pepsiMethod(String name) {
        throw new UnsupportedOperationException();
    }
    //模板方法3，按照业务娃哈哈可乐自己单独实现
    public String wahahaMethod(String name) {
        throw new UnsupportedOperationException();
    }

    protected void initResource()
    {
        System.out.println("init抽象类父类已经统一实现，给大家方便，子类也可以覆写");
    }

    //留给子类实现
    public abstract String invokeCommon();

    //void afterPropertiesSet() throws Exception;

}

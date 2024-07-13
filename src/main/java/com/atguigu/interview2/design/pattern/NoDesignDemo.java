package com.atguigu.interview2.design.pattern;

/**
 * @auther zzyy
 * @create 2024-03-07 16:55
 */
public class NoDesignDemo
{
    public void more_IfElse(String parameter)
    {
        /**
         * 思考，两个干掉：
         * 1 if...else if 判断分支本身里面的业务逻辑，比如  System.out.println("我是百事可乐");
         * 2 if...else if 判断分支本身
         */
        if ("Pepsi".equalsIgnoreCase(parameter)) {
            System.out.println("我是百事可乐-初始无设计");
            System.out.println("我是百事可乐-初始无设计");
            System.out.println("我是百事可乐-初始无设计");
            System.out.println("我是百事可乐-初始无设计");
            System.out.println("我是百事可乐-初始无设计");
            System.out.println("我是百事可乐-初始无设计");
            System.out.println("我是百事可乐-初始无设计");
            System.out.println("我是百事可乐-初始无设计");
            System.out.println("我是百事可乐-初始无设计");
        }else if ("Coca".equalsIgnoreCase(parameter)){
            System.out.println("我是可口可乐-初始无设计");
            if("618".equalsIgnoreCase(parameter))
            {
                //需要配合618积分平台大促活动，涉及复杂业务。
            }
            if("1111".equals(parameter))
            {
                //需要配合双11优惠卷平台大促活动，涉及复杂业务。
                //1 MQ同步消息给其它下游系统
                //2 xxl-job做好定时任务扫描，当晚下单下单顾客，没1分钟统计一次送给QTL系统
                //3 。。。。。。
            }
        }else if ("Wahaha".equalsIgnoreCase(parameter)){
            System.out.println("我是娃哈哈可乐-初始无设计");
        }
    }

    public static void main(String[] args)
    {
        new NoDesignDemo().more_IfElse("wahaha");
    }
}

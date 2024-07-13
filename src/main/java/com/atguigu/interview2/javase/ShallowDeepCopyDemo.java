package com.atguigu.interview2.javase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @auther zzyy
 * @create 2024-05-29 17:29
 */
public class ShallowDeepCopyDemo implements Cloneable
{
    public static void main(String[] args) throws CloneNotSupportedException
    {
        /*ShallowDeepCopyDemo shallowDeepCopyDemo = new ShallowDeepCopyDemo();

        Object o = shallowDeepCopyDemo.clone();

        System.out.println(o.hashCode());*/

        m1();
    }

    private static void m1() throws CloneNotSupportedException
    {
        Emp emp = new Emp("z3",15,"雷军","CEO");
        System.out.println("原始对象："+emp.getBoss().getTitle());

        Emp emp2 = (Emp)emp.clone();
        System.out.println("拷贝对象："+emp2.getBoss().getTitle());

        System.out.println();
        emp2.getBoss().setTitle("CTO");
        System.out.println("------emp2拷贝对象修改Title=CTO,是否会影响原始对象");


        System.out.println("原始对象："+emp.getBoss().getTitle());
        System.out.println("拷贝对象："+emp2.getBoss().getTitle());
    }
}









@Data
@AllArgsConstructor
@NoArgsConstructor
class Boss implements Cloneable
{
    private String bossName;
    private String title;

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Emp implements Cloneable
{
    private String empName;
    private Integer age;

    private Boss boss;

    public Emp(String empName, Integer age, String bossName,String title)
    {
        this.empName = empName;
        this.age = age;
        this.boss = new Boss(bossName,title);
    }

    /*@Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }*/

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return new Emp(empName,age,boss.getBossName(),boss.getTitle());
    }
}
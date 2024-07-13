package com.atguigu.interview2.javase;

import com.atguigu.interview2.entities.Person;

/**
 * @auther zzyy
 * @create 2024-05-01 21:11
 */
public class TransmitValueOrRef {
    public void changeValue1(int age){
        age = 30;
    }
    public void changeValue2(Person person){
        person.setPersonName("xxx");
    }
    public void changeValue3(String str){
        str = "xxx";
    }
    public static void main(String[] args){
        TransmitValueOrRef test = new TransmitValueOrRef();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age----"+age);//要求你打印出来的age到底是那个方法的变量？

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName-----"+person.getPersonName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("String-----"+str);
    }
}

package com.atguigu.interview2.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person
{
    //温馨提醒，
    // 本类没有覆写hashcode和equals方法
    private Integer id;
    private String  personName;

    public Person(String personName)
    {
        this.personName = personName;
    }
}

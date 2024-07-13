package com.atguigu.interview2.algorithm;

/**
 * @auther zzyy
 * @create 2024-05-14 18:56
 *左右指针-杀
 * 344. 反转字符串
 * https://leetcode.cn/problems/reverse-string/
 *
 * //结果比目标，小了要变大，左针右移+
 * //结果比目标，大了要变小，右针左移-
 */
public class LeetCode344
{
    public void reverseString(char[] s)
    {
        // 一左一右两个指针相向而行
        //结果比目标，小了要变大，左针右移+
        //结果比目标，大了要变小，右针左移-

        int left = 0;//左指针
        int right = s.length - 1; //右指针

        while(left < right)
        {
            //1 先交换
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            //2 移动指针
            left++;
            right--;
        }



    }
}

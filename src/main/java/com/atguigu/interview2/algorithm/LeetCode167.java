package com.atguigu.interview2.algorithm;

/**
 * @auther zzyy
 * @create 2024-05-14 19:25
 * 167. 两数之和 II - 输入有序数组
 * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
 */
public class LeetCode167
{
    public int[] twoSum(int[] numbers, int target)
    {
        // 一左一右两个指针相向而行
        int left = 0,right = numbers.length - 1;

        while(left < right)
        {
            int sum = numbers[left] + numbers[right];

            if(sum == target)
            {
                // 题目要求:给你一个下标从 1 开始的整数数组 numbers...所以两个下标指针都加个1
                return new int[]{left + 1 ,right + 1};
            }else if(sum < target){
                left++; //让sum大一点，左指针向右变大
            }else if(sum > target){
                right--;    //让sum小一点，右指针向左变小
            }
        }

        return new int[]{-1,-1};
    }
}

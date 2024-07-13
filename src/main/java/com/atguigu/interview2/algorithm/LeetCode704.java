package com.atguigu.interview2.algorithm;

/**
 * @auther zzyy
 * @create 2024-05-14 19:18
 */
public class LeetCode704
{
    public int search(int[] nums, int target) {
        // 一左一右两个指针相向而行
        int left = 0,right = nums.length - 1;
        while(left <= right)
        {
            int middle = (right + left)/2;
            if(nums[middle] == target)
            {
                return middle;// 数组中找到目标值，直接返回下标
            }else if(nums[middle] < target){
                left = middle + 1;  //结果比目标，小了要变大，左针右移+
            }else if(nums[middle] > target){
                right = middle - 1;//结果比目标，大了要变小，右针左移-
            }
        }
        return - 1;
    }
}

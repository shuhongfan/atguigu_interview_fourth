package com.atguigu.interview2.algorithm;

/**
 * @auther zzyy
 * @create 2024-05-14 20:42
 *
 *  27. 移除元素
 *  https://leetcode.cn/problems/remove-element/
 *
 *
快慢相等值不变，慢针不动快针走；
快慢不等值，我是题型2，快针慢针值互换，慢针向前一步走，快针向前一步走；
 */
public class LeetCode027 //家庭作业
{
    public int removeElement(int[] nums, int val)
    {
        int fast=0,slow=0;
        while (fast < nums.length)
        {
            if (nums[fast] != val)
            {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}

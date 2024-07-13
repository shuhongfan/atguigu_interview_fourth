package com.atguigu.interview2.algorithm;

/**
 * @auther zzyy
 * @create 2024-05-14 20:24
 *
 * 283. 移动零
 * https://leetcode.cn/problems/move-zeroes/description/
 *
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 输入: nums = [0,1,0,3,12]
 *
 * 输出: [1,3,12,0,0]
 *
快慢相等值不变，慢针不动快针走；
快慢不等值，我是题型2，快针慢针值互换，慢针向前一步走，快针向前一步走；
 */
public class LeetCode283 //家庭作业
{
    public void moveZeroes(int[] nums)
    {
        if (nums == null) return;

        int fast=0,slow=0;
        while (fast < nums.length)
        {
            if(nums[fast] != 0)
            {
                //1 通过交换，快针赋值给慢针
                int tmp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = tmp;
                //2 慢针向前一步走
                slow++;
            }
            //3 快针向前一步走
            fast++;
        }

        //4 自己测试，输出结果
        /*for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }*/
    }

    public static void main(String[] args)
    {
        LeetCode283 test = new LeetCode283();
        test.moveZeroes(new int[]{0,1,0,3,12});
    }
}

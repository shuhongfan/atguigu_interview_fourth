package com.atguigu.interview2.algorithm;

/**
 * @auther zzyy
 * @create 2024-05-14 19:11
 *
 * 26. 删除有序数组中的重复项
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/
 *
 * 快慢相等值不变，慢针不动快针走；
 * 快慢不等值，我是题型1，慢针向前一步走，快针赋值给慢针，快针向前一步走；
 */
public class LeetCode026
{
    /**
     输入：nums = [0,0,1,1,1,2,2,3,3,4]
     输出：5, nums = [0,1,2,3,4]
     解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。
     不需要考虑数组中超出新长度后面的元素。
     * */
    public int removeDuplicates(int[] nums) {
        int fast = 0,slow = 0;
        while(fast < nums.length)
        {
            if(nums[fast] != nums[slow])
            {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 数组长度为索引 + 1
        return slow + 1;
    }
}

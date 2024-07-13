package com.atguigu.interview2.algorithm;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther zzyy
 * @create 2024-05-14 18:59
 *
 * 1 两数之和
 * https://leetcode.cn/problems/two-sum/
 */
public class LeetCode001
{
    /**
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target)
    {
        for (int i = 0; i < nums.length; i++)
        {
            for (int j = i+1 ; j < nums.length ; j++)
            {
                if(nums[i] + nums[j] == target)
                {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target)
    {
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        /**
         *输入：nums = [2,7,11,15], target = 9
         *输出：[0,1]
         */
        for (int i = 0; i < nums.length; i++)
        {
            //伙伴数
            int partnerNumber = target - nums[i];

            if(map.containsKey(partnerNumber))
            {
                return new int[]{map.get(partnerNumber),i};
            }
            /**
             *  map  k       v(数组下标)
             *1      2       0
             *2      7       1
             *3      11      2
             */
            map.put(nums[i],i);
        }
        return null;
    }

    public static void main(String[] args)
    {
        LeetCode001 test = new LeetCode001();

        int[] nums = new int[]{2,7,11,15};
        int target = 9;


        int[] ints = test.twoSum2(nums, target);

        for (int element : ints) {
            System.out.println(element);
        }
    }
}

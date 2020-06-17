package com.leetcode.test.middle;

/**
 * @Author: Chenwx
 * @Date: 2020/6/17 15:33
 */
public class SearchRange {
    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        int[] nums = {1, 3};
        int target = 3;
        int[] ints = searchRange.searchRange(nums, target);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    /*

        给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

        你的算法时间复杂度必须是 O(log n) 级别。

        如果数组中不存在目标值，返回 [-1, -1]。


      示例1:      输入: nums = [5,7,7,8,8,10], target = 8
                  输出: [3,4]

     */
    private int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1,-1};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                res[0] = i;
                for (int j = nums.length - 1; j > i; j--) {
                    if (nums[j] == target) {
                        res[1] = j;
                        return res;
                    }
                }
                res[1] = i;
                return res;
            }
        }
        return res;
    }

    /**
     *
     * 解题思路: 正序查询是否与目标相等的数值，如果有，记录下标。 如果没有返回[-1,-1]
     *          进行倒序查询至第一次找到的位置，如果有，记录下标。 如果没有返回[i,i] (i为第一次记录的下标)
     *
     */
}

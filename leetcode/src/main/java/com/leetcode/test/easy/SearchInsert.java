package com.leetcode.test.easy;

/**
 * @Author: Chenwx
 * @Date: 2020/6/17 16:38
 */
public class SearchInsert {
    public static void main(String[] args) {
        int[] nums = {1};
        int target = 0;
        SearchInsert searchInsert = new SearchInsert();
        int res = searchInsert.searchInsert(nums, target);
        System.out.println(res);
    }

    /*
        给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

        你可以假设数组中无重复元素。

        示例1：
            输入: [1,3,5,6], 5
            输出: 2
     */
    private int searchInsert(int[] nums, int target) {
        int res = 0;
        if (nums.length == 1) {
            res = nums[0] < target ? 1 : 0;
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            //判断是否相等
            if (nums[i] == target) {
                res = i;
                break;
            }
            //判断是否为最后一个
            if (i == nums.length - 1) {
                res = nums.length;
            } else if (nums[i] > target) {
                res = i;
                break;
            } else if (nums[i] < target && nums[i + 1] > target) {
                res = i + 1;
                break;
            }
        }
        return res;
    }
}

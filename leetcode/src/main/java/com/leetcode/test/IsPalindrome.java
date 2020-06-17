package com.leetcode.test;

/**
 * @Author: Chenwx
 * @Date: 2020/6/17 10:55
 */
public class IsPalindrome {
    public static void main(String[] args) {

    }

    //判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int res = 0;
        while (x != 0) {
            int remainder = x % 10;
            res = res * 10 + remainder;
            x = x / 10;
        }
        if (res == x) {
            return true;
        }
        return false;
    }
}

package com.leetcode.test.easy;

import java.util.Scanner;

/**
 * @Author: Chenwx
 * @Date: 2020/6/17 10:55
 */
public class IsPalindrome {
    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数:");
        int i = scanner.nextInt();
        boolean res = isPalindrome.isPalindrome(i);
        System.out.println("是否为回文数：" + res);
    }

    //判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
    private boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int start = x;
        int res = 0;
        while (x != 0) {
            int remainder = x % 10;
            res = res * 10 + remainder;
            x = x / 10;
        }
        return res == start;
    }
}

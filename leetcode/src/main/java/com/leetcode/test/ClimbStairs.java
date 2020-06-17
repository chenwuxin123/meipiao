package com.leetcode.test;

/**
 * @Author: Chenwx
 * @Date: 2020/6/17 10:50
 */
public class ClimbStairs {

    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        int i = climbStairs.climbStairs(10);
        System.out.println("总共有" + i + "种方法");
    }

    //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    //
    //每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    public int climbStairs(int n) {
        int dp[] = new int[n + 1];
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}



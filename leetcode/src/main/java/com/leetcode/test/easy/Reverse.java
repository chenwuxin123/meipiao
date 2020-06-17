package com.leetcode.test.easy;

/**
 * @Author: Chenwx
 * @Date: 2020/6/17 10:54
 */
public class Reverse {

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        int res = reverse.reverse(125);
        System.out.println("获取的反转数:" + res);
    }

    //给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
    //
    //示例 : 输入: 120 输出: 21
    private int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int remainder = x % 10;
            //判断是否 大于 最大32位整数
            if (res > 214748364 || (res == 214748364 && remainder > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < -214748364 || (res == -214748364 && remainder < -8)) {
                return 0;
            }
            res = res * 10 + remainder;
            x = x / 10;
        }
        return res;
    }

}

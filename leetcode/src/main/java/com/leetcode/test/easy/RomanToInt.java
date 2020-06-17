package com.leetcode.test.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Chenwx
 * @Date: 2020/6/17 11:23
 */
public class RomanToInt {
    public static void main(String[] args) {
        RomanToInt romanToInt = new RomanToInt();
        int res = romanToInt.romanToInt("MCMXCIV");
        System.out.println("得到的结果为:" + res);
    }

    /*
                字符          数值
                I             1
                V             5
                X             10
                L             50
                C             100
                D             500
                M             1000


    例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

    通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

    I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
    X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
    C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
    给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

        示例:
            输入: "LVIII"
            输出: 58
            解释: L = 50, V= 5, III = 3.


     */
    static Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    private int romanToInt(String s) {
        int res = 0;
        int length = s.length();

        res += map.get(s.charAt(0));
        if (length == 1)
            return res;

        for (int i = 1; i < length; i++) {
            int before = map.get(s.charAt(i - 1));
            int current = map.get(s.charAt(i));
            if (before < current)
                res -= 2 * before;

            res += current;
        }
        return res;
    }
    /**
     *解题思路
     *
     *将罗马数字和整数的对应关系存入Map。
     *首先将result+第1位罗马数字对应的整数值，
     *然后从第二位罗马数字开始遍历：取出前一位（第i-1位）罗马数字对应的整数值，再获得当前罗马数字（第i位）对应的整数值
     * 1.若“前一位的值”大于等于“当前位的值”，直接将result+“当前一位的值”；
     * 2.若“前一位的值”小于“当前位的值”，先将result-“前一位的值”*2，再将result+“当前位的值”。
     *
     */
}

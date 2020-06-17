package com.leetcode.test.easy;

/**
 * @Author: Chenwx
 * @Date: 2020/6/17 14:29
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String[] srts = {""};
        String prefix = longestCommonPrefix.longestCommonPrefix(srts);
        System.out.println("公共前缀:" + prefix);
    }

    //编写一个函数来查找字符串数组中的最长公共前缀。
    //如果不存在公共前缀，返回空字符串 ""。
    /*
        示例 1:
        输入: ["flower","flow","flight"]
        输出: "fl"
     */
    private String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs.length; i++) {
            if(strs[0].equals("")){
                return "";
            }
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}

package com.leetcode.test.easy;

import java.util.HashMap;

/**
 * @Author: Chenwx
 * @Date: 2020/6/17 15:04
 */
public class IsValid {
    public static void main(String[] args) {
        IsValid isValid = new IsValid();
        boolean res = isValid.isValid("(){}");
        System.out.println(res);
    }

    /*
        给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

        有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        注意空字符串可被认为是有效字符串。

     */

    private boolean isValid(String s) {
        char[] charArray = new char[s.length() + 1]; //作为栈

        int p = 1;
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                charArray[p++] = c;
            } else {
                p--;
                if (c == ')' && charArray[p] != '(') {
                    return false;
                }
                if (c == '}' && charArray[p] != '{') {
                    return false;
                }
                if (c == ']' && charArray[p] != '[') {
                    return false;
                }
            }
        }
        return p == 1;
    }

    /**
     *  解题思路：
     *      利用栈，先进左边({[ ,然后在判断后面有没有相反的括号，如果有就删除临近的
     *      最终，判断栈中是否存在元素
     *   栈的思想重要
     */
}

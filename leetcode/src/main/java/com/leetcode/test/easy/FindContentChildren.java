package com.leetcode.test.easy;

import java.util.Arrays;

/**
 * @Author: Chenwx
 * @Date: 2020/6/18 14:59
 */
public class FindContentChildren {
    public static void main(String[] args) {
        FindContentChildren findContentChildren = new FindContentChildren();
        int[] g = {7, 8, 9, 10};
        int[] s = {5, 6, 7, 8, 9};
        int res = findContentChildren.findContentChildren(g, s);
        System.out.println(res);
    }

    /*

    假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；
    并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

    注意：

    你可以假设胃口值为正。
    一个小朋友最多只能拥有一块饼干。


     */
    private int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;//胃口下标指针
        int j = 0;//饼干下标指针
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }

    /**
     *
     * 贪心算法，又称贪婪算法(Greedy Algorithm)，是指在对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优解出发来考虑，它所做出的仅是在某种意义上的局部最优解
     *
     *
     */
}

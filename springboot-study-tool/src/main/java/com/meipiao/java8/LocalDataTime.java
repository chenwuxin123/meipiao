package com.meipiao.java8;

/**
 * @Author: Chenwx
 * @Date: 2020/6/23 10:06
 */
public class LocalDataTime {
    public static void main(String[] args) {
        int count = 0;
        while (true) {
            System.out.println("现在的count是" + count);
            count++;
            while (count == 7) {
                System.out.println("该吃饭了");
                count++;
            }
        }
    }
}

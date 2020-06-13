package com.meipiao.java8;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Author: Chenwx
 * @Date: 2020/6/9 10:12
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        List<String> list1 = list.subList(8, list.size());
        for (String s : list1) {
            System.out.println(s);
        }
    }


}


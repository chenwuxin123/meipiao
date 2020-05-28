package com.meipiao.java8;


import com.alibaba.fastjson.JSONObject;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Lambda 表达式的使用
 *
 * @Author: Chenwx
 * @Date: 2020/5/11 14:32
 */
public class Lambda {
    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 running...");
            }
        }).start();
        Thread.sleep(1000);

        //使用Lambda表达式开启线程
        new Thread(() -> {
            System.out.println("thread2 running...");
        }).start();

        GreetingService greetingService = (a) -> {
            System.out.println("写入的参数为" + a);
            return "dog";
        };
        String info = greetingService.getCount(18);
        System.out.println("返回的数据为:" + info);

        //使用forEach遍历数组
        ArrayList<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.forEach((item) -> {
            System.out.println("我是forEach遍历出来的元素:" + item);
        });

        HashMap<String, String> map = new HashMap<>();
        map.put("name", "susan");
        map.put("address", "qingdao");
        map.forEach((k, v) -> {
            System.out.println(k + "\t" + v);
        });

        HashMap<Object, Object> map1 = new HashMap<>();
        List<String> a = new ArrayList<>();
        a.add("{\"count\":1139418,\"QueryTime\":\"2020-05-27 09:00:02\",\"TimeStamp\":1590570002049}");
        a.add("{\"count\":1151385,\"QueryTime\":\"2020-05-27 14:00:02\",\"TimeStamp\":1590588002207}");
        for (String single : a) {
            JSONObject jsonObject = JSONObject.parseObject(single);
            String count = jsonObject.getString("count");
            String QueryTime = jsonObject.getString("QueryTime");
            map1.put("count", count);
            map1.put("QueryTime", QueryTime);
        }
        System.err.println(map1.toString());

    }
}

//函数式接口
@FunctionalInterface
interface GreetingService {
    String getCount(int count);
}
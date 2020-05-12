package com.meipiao.java8;


import java.util.ArrayList;

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
        list.forEach((item)-> {
            System.out.println("我是forEach遍历出来的元素:"+item);
        });
    }
}

//函数式接口
@FunctionalInterface
interface GreetingService {
    String getCount(int count);
}
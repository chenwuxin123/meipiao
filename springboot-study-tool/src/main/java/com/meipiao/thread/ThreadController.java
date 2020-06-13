package com.meipiao.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: Chenwx
 * @Date: 2020/6/13 10:07
 */
//多线程测试
@RestController
@RequestMapping("/thread")
public class ThreadController {

    @Async
    @GetMapping("/pull")
    public void pull(List hotelIds) throws InterruptedException {
        for (Object hotelId : hotelIds) {
            String name = Thread.currentThread().getName();
            System.err.println("线程:" + name + "正在拉取数据: " + hotelId);
            Thread.sleep(5000);//模拟正在执行任务
            System.err.println("线程:" + name + "   数据:" + hotelId + " 拉取完毕!");
        }

    }

    @RequestMapping("/start")
    public void start() {
        //创建只有3个线程的线程池
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        //根据需要拉取的数据平均分配给每个线程(例如3个线程总共查询到共有300个酒店id待拉取，每个线程分配100个酒店id 实现异步拉取数据)
        //先从mongodb中查询到所有的id的数量 all在%ThreadCount = count
        ArrayList<String> list = new ArrayList<>();
        list.add("aa1");
        list.add("aa2");
        list.add("aa3");
        list.add("aa4");
        list.add("aa5");
        list.add("aa6");
        list.add("aa7");
        list.add("aa8");
        list.add("aa9");
        list.add("aa10");
        int threadCount = 3;//线程数
        //解析list 均等分给线程
        int total = list.size();
        int copy = total / threadCount;
        for (int i = 0; i < threadCount; i++) {
            List<String> subList;
            if (i != threadCount - 1) {
                subList = list.subList(copy * i, copy * (i + 1));// fromIndex（包括 ）和 toIndex（不包括）
            } else {
                subList = list.subList(copy * i, total);
            }
            executorService.execute(() -> {
                try {
                    pull(subList);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}

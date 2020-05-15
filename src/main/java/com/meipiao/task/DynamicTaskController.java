package com.meipiao.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务的开启、关闭、修改执行周期 利用线程池来执行
 * @Author: Chenwx
 * @Date: 2020/5/12 10:29
 */
@RestController
@RequestMapping("/task")
@EnableScheduling
@Slf4j
public class DynamicTaskController {
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskScheduler taskExecutor;

    private ScheduledFuture<?> future;

    //future集合
    private LinkedList<Future> futureList;

    //任务每秒执行次数
    private Integer executeCount = 3;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @RequestMapping("/startCron")
    public String startCron() {

        future = taskExecutor.schedule(new MyRunnable(), new CronTrigger("0/5 * * * * *"));
        System.out.println("DynamicTask.startCron()");
        return "startCron";
    }

    @RequestMapping("/stopCron")
    public String stopCron() {

        if (future != null) {
            future.cancel(true);
        }
        System.out.println("DynamicTask.stopCron()");
        return "stopCron";
    }

    @RequestMapping("/changeCron10")
    public String startCron10() {

        stopCron();// 先停止，在开启.
        future = taskExecutor.schedule(new MyRunnable(), new CronTrigger("*/10 * * * * *"));
        System.out.println("DynamicTask.startCron10()");
        return "changeCron10";
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("DynamicTask.MyRunnable.run()，" + LocalDateTime.now());
        }
    }


    /**
     * 应用场景：开启多线程定时执行任务，异步方法
     * 例如：每秒通过10个线程，访问接口拉取数据。数据拉取后去重写入数据库 达到拉取最大值
     * 注意事项：详细见README.md
     */
 /*
       开启线程池定时进行异步拉取
       自己创建的线程+Tomcat线程http-nio-30054-exec-1
       stop/start
    */
    @RequestMapping("/startAsync")
    public void startAsync() {
        futureList = new LinkedList<>();
        for (int i = 0; i < executeCount; i++) {
            ScheduledFuture<?> future = taskExecutor.schedule(() -> {
                String name = Thread.currentThread().getName();
                String say = say(name);
                System.out.println(say);
            }, new CronTrigger("0/5 * * * * ? "));
            futureList.add(future);
        }

    }

    /*
           关闭任务
     */
    @RequestMapping("/stopAsync")
    public String shutdown() {
        if(null != futureList && futureList.size() >0 ){
            for (Future future : futureList) {
                future.cancel(true);
            }
        }
        log.info("{}: 已经关闭startAsync任务", LocalDateTime.now());
        return "stop async method increment hotel data... ";
    }

    //开启异步方法
    @Async
    public String say(String name) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now + " :线程" + name + "执行了");
        return "success :" + name;
    }
}

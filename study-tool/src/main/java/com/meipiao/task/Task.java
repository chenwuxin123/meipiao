package com.meipiao.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 简单定时任务，固定时期执行
 * @Author: Chenwx
 * @Date: 2020/5/11 14:21
 */
@Configuration
@EnableScheduling
public class Task {
    /**
     * 每一秒中执行以下改方法
     * cron是用来指定执行的 秒，分钟，日期等
     */
//    @Scheduled(cron="0/10 * * * * *")
    public void task(){
        //调用接口，定时执行
        System.out.println("需要执行的任务");
    }
}

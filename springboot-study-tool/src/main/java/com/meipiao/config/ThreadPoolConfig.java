package com.meipiao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池任务调度器，能够开启线程池进行任务调度
 * @Author: Chenwx
 * @Date: 2020/5/15 8:41
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    public static final Integer poolSize = 10;                      //线程池大小
    public static final String threadNamePrefix = "incrementDataExecutor-";  //线程池名的前缀

    @Bean("taskExecutor")
    public ThreadPoolTaskScheduler taskExecutor(){
        ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
        taskExecutor.setPoolSize(poolSize);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        // 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }
}

package com.meipiao.task;

import lombok.extern.slf4j.Slf4j;
import org.anyline.util.BasicUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

/**
 *  Simple adapter for {@link SchedulingConfigurer} 可以更改任务执行的时间
 * @Author: Chenwx
 * @Date: 2020/5/11 14:12
 *
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class ScheduleTask implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                    try {
                        //调用接口，定时执行
                        System.out.println("需要执行的任务");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    log.info("执行动态定时任务: {}", LocalDateTime.now().toLocalTime());
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = "0 */5 * * * ?";
                    //2.2 合法性校验.
                    if (BasicUtil.isEmpty(cron)) {
                        // Omitted Code ..
                        cron = "0 */30 * * * ?";
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}

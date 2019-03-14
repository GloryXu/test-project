package com.redsun.quartz.db;

import com.redsun.quartz.HelloJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author xuguangrong
 * @description 定时器简单的例子，使用SimpleTriggerImpl
 * @date Created at 21:54 2019/3/8
 */
@Slf4j
public class SimpleExample {

    public static void main(String[] args) throws Exception {
        SimpleExample example = new SimpleExample();
        example.run();
    }

    public void run() throws Exception {
        log.info("------- Initializing ----------------------");
        // 通过调度器工厂获取调度器，初始化工程时须指定其使用我们自己的配置文件
        SchedulerFactory stdSchedulerFactory = new StdSchedulerFactory("quartz/quartz-db.properties");
        Scheduler scheduler = stdSchedulerFactory.getScheduler();

        // 这儿clear一下，因为使用数据库储存方式时，shutdown的时候没有清除，第二次运行会报Job is already exist
        scheduler.clear();
        log.info("------- Initialization Complete -----------");

        Date runTime = DateBuilder.evenMinuteDate(new Date());

        log.info("------- Scheduling Job  -------------------");

        // 创建任务详情
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
        // 创建触发器
        SimpleTriggerImpl trigger = (SimpleTriggerImpl) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(new Date()).build();
        trigger.setRepeatCount(5);// 五次
        trigger.setRepeatInterval(3000);// 三秒
        log.info("------- Start time =  " + trigger.getStartTime() + " -----------------");

        // 调度器、触发器、任务，三者关联
        scheduler.scheduleJob(job, trigger);

        log.info(job.getKey() + " will run at: " + runTime);
        // 调度启动
        scheduler.start();
        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting 1 minute... -------------");
        try {
            Thread.sleep(60000L);
        } catch (Exception e) {
        }

        log.info("------- Shutting Down ---------------------");
        // 调度关闭
        scheduler.shutdown(true);
        log.info("------- Shutdown Complete -----------------");
    }

}

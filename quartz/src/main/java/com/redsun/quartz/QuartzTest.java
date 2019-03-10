package com.redsun.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author xuguangrong
 * @description QuartzTest
 * @date Created at 20:33 2019/3/8
 */
public class QuartzTest {
    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1").build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
                    .startNow().withSchedule(simpleSchedule()
                            .withIntervalInSeconds(40).repeatForever()).build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            Thread.sleep(60000);

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

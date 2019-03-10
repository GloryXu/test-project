package com.redsun.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author xuguangrong
 * @description
 * @date Created at 20:36 2019/3/8
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("------------------------lsjdfsd");
    }
}

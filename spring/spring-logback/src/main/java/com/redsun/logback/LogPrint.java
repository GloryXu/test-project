package com.redsun.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuguangrong
 * @description 日志打印
 * @date Created at 16:26 2019/6/8
 */
public class LogPrint {
    protected static final Logger mylogger = LoggerFactory.getLogger("myLogger");

    private AtomicInteger count;

    public LogPrint(AtomicInteger count) {
        this.count = count;
    }

    private ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);

    public void printInfo () {
        pool.scheduleAtFixedRate(() -> {
            mylogger.info("print count " + count.incrementAndGet());
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }
}

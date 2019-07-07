package com.redsun.logback;

import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author xuguangrong
 * @description 自定义 handler 策略
 * @date Created at 16:18 2019/6/8
 */
@Slf4j
public class MyPolicy extends ContextAwareBase implements LifeCycle {

    private String appName;

    private boolean start;

    @Override
    public void start() {
        log.info("MyPolicy start !");
        this.start = true;
    }

    @Override
    public void stop() {
        log.info("MyPolicy stop !");
        this.start = false;
    }

    @Override
    public boolean isStarted() {
        return start;
    }

    public void handler(String message) {
        log.info("log print message:" + message);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}

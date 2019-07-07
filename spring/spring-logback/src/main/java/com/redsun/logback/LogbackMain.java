package com.redsun.logback;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuguangrong
 * @description 日志打印启动类
 * @date Created at 16:47 2019/6/8
 */
public class LogbackMain {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

        new LogPrint(new AtomicInteger(1)).printInfo();

        System.in.read();
    }

}

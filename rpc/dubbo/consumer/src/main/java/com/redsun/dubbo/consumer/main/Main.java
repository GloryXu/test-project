package com.redsun.dubbo.consumer.main;

import com.redsun.dubbo.consumer.Consumer;
import com.redsun.dubbo.provider.ProviderI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by xugr on 2017/3/22.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        ProviderI providerI = (ProviderI)context.getBean("providerI");
        Consumer consumer = (Consumer)context.getBean("consumer");
        consumer.testProvider();

        System.in.read();
    }
}

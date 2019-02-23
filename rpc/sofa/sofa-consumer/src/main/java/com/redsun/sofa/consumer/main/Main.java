package com.redsun.sofa.consumer.main;

import com.redsun.sofa.consumer.Consumer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xuguangrong
 * @description main类
 * @date Created at 15:44 2019/2/21
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

        Consumer consumer = (Consumer)context.getBean("consumer");
        consumer.testProvider();
    }

}

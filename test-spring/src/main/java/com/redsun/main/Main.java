package com.redsun.main;

import com.redsun.SuperClass;
import com.redsun.sub.SubClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        SuperClass superClass = (SuperClass)context.getBean("superClass");
        SubClass subClass = (SubClass)context.getBean("subClass");
        subClass.printLog();
        superClass.printLog();
    }
}

package com.redsun.initialclass.samples.three;

/**
 * 静态常量在编译阶段就会被存入调用类的常量池中，不会引用到定义常量的类,不会触发类的初始化
 */
public class ConstClass {
    static {
        System.out.println("常量类初始化！");
    }

    public static final String HELLOWORLD = "hello world!";
}

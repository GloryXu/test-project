package com.redsun.initialclass.samples.two;

public class SuperClass {
    //静态变量value
    public static int value = 666;

    //静态块，父类初始化时会调用
    static {
        System.out.println("父类初始化！");
    }
}

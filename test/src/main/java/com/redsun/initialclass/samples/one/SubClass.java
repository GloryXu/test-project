package com.redsun.initialclass.samples.one;

public class SubClass extends SuperClass {
    //静态块，子类初始化时会调用
    static {
        System.out.println("子类初始化！");
    }
}

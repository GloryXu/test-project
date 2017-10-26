package com.redsun.initialclass.samples.one;

/**
 * 通过子类引用父类的静态字段，对于父类属于“主动引用”的第一种情况，
 * 对于子类，没有符合“主动引用”的情况，故子类不会进行初始化
 */
public class NotInit {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}

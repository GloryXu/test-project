package com.redsun.classload;

import org.junit.Test;

class Dog {
    // ②
    {
        System.out.println("this is a non-static block!");
    }

    // ①
    static {
        System.out.println("this is a static block!");
    }

    // ③
    public Dog() {
        System.out.println("this is a no-parameter construct!");
    }
}

public class StaticInitTest2 {

    @Test
    public void main() {
        Dog dog = new Dog();
    }
}

package com.redsun.classload;


import org.junit.Test;

public class InitTest {

    @Test
    public void main() {
        Cat cat = new Cat("kitty", 2);
        System.out.println(cat);
        Cat cat2 = new Cat("Jerfield", 3);
        System.out.println(cat2);
    }


    class Cat {
        //定义name,age两个实例变量
        String name;
        int age;

        //使用构造器初始化name,age两个实例变量
        public Cat(String name, int age) {
            System.out.println("执行构造器");
            this.name = name;
            this.age = age;
        }

        {
            System.out.println("执行非静态初始化块");
            weight = 2.0;
        }

        //定义时指定初始值
        double weight = 2.3;

        @Override
        public String toString() {
            return "Cat[name=" + name + ",age=" + age + ",weight=" + weight + "]";
        }
    }
}

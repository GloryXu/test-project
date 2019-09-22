package classloader.entity;

/**
 * @author xuguangrong
 * @description
 * @date Created at 13:07 2019/9/22
 */
public class Cat {
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

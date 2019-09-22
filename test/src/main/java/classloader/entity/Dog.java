package classloader.entity;

/**
 * @author xuguangrong
 * @description
 * @date Created at 12:58 2019/9/22
 */
public class Dog {
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

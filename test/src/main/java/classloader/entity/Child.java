package classloader.entity;

/**
 * @author xuguangrong
 * @description
 * @date Created at 13:00 2019/9/22
 */
public class Child extends Father {
    static {
        System.out.println("执行了子类静态语句块");
    }
}
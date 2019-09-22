package classloader.entity;

/**
 * @author xuguangrong
 * @description
 * @date Created at 13:01 2019/9/22
 */
public class Father extends Super {
    //    public static int m = 33;
    public static int m = 33;

    static {
        System.out.println("执行了父类静态语句块");
    }
}

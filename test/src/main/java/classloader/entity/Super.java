package classloader.entity;

/**
 * @author xuguangrong
 * @description
 * @date Created at 13:01 2019/9/22
 */
public class Super {
    public static int m = 11;

    static {
        System.out.println("执行了super类静态语句块");
    }
}

package synchronize.sample;

/**
 * 静态类加锁开发
 *
 * @author xuguangrong
 * @description 静态方法测试类
 * @date Created at 15:40 2018/12/28
 */

public class StaticClass {

    public static synchronized void  printOne() {
        System.out.println("----------11111");

        try {
            System.out.println("--------------" + Thread.currentThread().getName());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void printTwo() {
        System.out.println("---------------22222");

        try {
            System.out.println("-----------" + Thread.currentThread().getName());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

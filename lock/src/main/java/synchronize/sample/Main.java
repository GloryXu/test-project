package synchronize.sample;

import java.io.Console;
import java.io.IOException;

/**
 * @author xuguangrong
 * @description Main函数
 * @date Created at 18:11 2018/12/28
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread thread1 = new Thread(() -> {
            StaticClass.printOne();
        });

        Thread thread2 = new Thread(() -> {
            StaticClass.printTwo();
        });

        thread1.start();

        Thread.sleep(200);
        thread2.start();

        System.in.read();
    }

}

package count.down.latch;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch c = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("pre print 1");
            c.countDown();
        }).start();

        new Thread(() -> {
            System.out.println("pre print 2");
            c.countDown();
        }).start();

        c.await();
        System.out.println("print 3");

    }
}

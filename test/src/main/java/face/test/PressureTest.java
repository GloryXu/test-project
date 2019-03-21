package face.test;

import java.util.concurrent.CountDownLatch;

public class PressureTest implements Runnable {

    private CountDownLatch downLatch;

    public PressureTest(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    public void run() {
        System.out.println("正在加载资源......");
        try {
            this.downLatch.await();
        } catch (InterruptedException e) {
        }
        System.out.println("资源加载好了，开始压测！");
    }
}
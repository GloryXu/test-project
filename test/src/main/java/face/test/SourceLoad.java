package face.test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SourceLoad implements Runnable {

    private CountDownLatch downLatch;
    private String name;

    public SourceLoad(CountDownLatch downLatch, String name) {
        this.downLatch = downLatch;
        this.name = name;
    }

    public void run() {
        this.doWork();
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        System.out.println("--------- " + this.name + "加载完了！");
        this.downLatch.countDown();
    }

    private void doWork() {
        System.out.println(this.name + " ,正在加载资源!");
    }



}
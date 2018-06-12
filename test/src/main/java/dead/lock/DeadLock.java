package dead.lock;

import java.util.Date;

public class DeadLock {
    public static String bowl = "碗";
    public static String chopsticks = "筷子";

    public static void main(String[] args) {
        LockA la = new LockA();
        new Thread(la).start();
        LockB lb = new LockB();
        new Thread(lb).start();
    }
}

class LockA implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(new Date().toString() + "邹保健开始拿餐具吃饭");
            while (true) {
                synchronized (DeadLock.bowl) {
                    System.out.println(new Date().toString() + "邹保健抢到了碗");
                    Thread.sleep(3000); // 此处等待是给B能锁住机会
                    synchronized (DeadLock.chopsticks) {
                        System.out.println(new Date().toString() + "邹保健抢到了筷子");
                        Thread.sleep(60 * 1000); // 为测试，占用了就不放
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class LockB implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println(new Date().toString() + "陈顶天开始拿餐具吃饭");
            while (true) {
                synchronized (DeadLock.chopsticks) {
                    System.out.println(new Date().toString() + "陈顶天抢到了筷子");
                    Thread.sleep(3000); // 此处等待是给A能锁住机会
                    synchronized (DeadLock.bowl) {
                        System.out.println(new Date().toString() + "陈顶天抢到了碗");
                        Thread.sleep(60 * 1000); // 为测试，占用了就不放
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

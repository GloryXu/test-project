package threadpool.queue;

/**
 * @author xuguangrong
 * @description 线程执行类
 * @date Created at 16:21 2019/7/21
 */
public class TestRunnable implements Runnable {

    private int count;

    private Object lock = new Object();

    public TestRunnable(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        if (count == 3 || count == 5) {
            synchronized (lock) {
                try {
                    lock.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("----count = " + count);
    }
}

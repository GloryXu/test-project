package threadpool.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author xuguangrong
 * @description 同步queue和Linked queue比较
 * @date Created at 10:56 2019/10/29
 */
public class SyncQueueLinkedQueueMain {

    private static final ThreadPoolExecutor linkedBlockingQueueThreadPoolExecutor = new ThreadPoolExecutor(50,
            90,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(9));


    private static final ThreadPoolExecutor synchronousThreadPoolExecutor = new ThreadPoolExecutor(50,
            90,
            60,
            TimeUnit.SECONDS,
            new SynchronousQueue());

    public static void main(String[] args) throws InterruptedException {
        int taskSizePerThread = 1;

        Object obj = new Object();
        int threadSize = 100;
        for (int i = 0; i < threadSize; i++) {
            new Thread(() -> {
                    List<TestTask> testTasks = new ArrayList<>();
                    for (int j = 0;j < taskSizePerThread;j++) {
                        testTasks.add(new TestTask());
                    }
                    synchronized (obj) {
                        try {
                            System.out.println("线程:" + Thread.currentThread().getName() + "等待运行");
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                try {
                    synchronousThreadPoolExecutor.invokeAll(testTasks, 200, TimeUnit.MILLISECONDS);
//                    linkedBlockingQueueThreadPoolExecutor.invokeAll(testTasks, 200, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(1000);
        System.out.println("开始运行啦~~~");
        synchronized(obj) {
            obj.notifyAll();
        }
    }
}

class TestTask implements Callable<String> {

    @Override
    public String call() {
        try {
            System.out.println("线程:" + Thread.currentThread().getName() + "先睡一会儿");
            Thread.sleep(210);
        } catch (InterruptedException e) {
            System.out.println("线程:" + Thread.currentThread().getName() + "被打断了");
        }
        return Thread.currentThread().getName();
    }
}

package threadpool.queue;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author xuguangrong
 * @description 测试线程池的queue，有界  or  无界
 * @date Created at 16:19 2019/7/21
 */
public class ThreadPoolMain {

    public static final int CORE_SIZE = 2;

    public static final int MAX_SIZE = 5;

    public static void main(String[] args) throws IOException {
//        BlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
        BlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_SIZE,
                MAX_SIZE,
                10,
                TimeUnit.SECONDS,
                linkedBlockingQueue);

        for (int i = 0;i < 20;i++) {
            TestRunnable testRunnable = new TestRunnable(i);
            try {
                threadPoolExecutor.execute(testRunnable);
            } catch (RejectedExecutionException e) {
                System.out.println("submit failed. " + e.getMessage());
                continue;
            }
            System.out.println("submit success，i = " + i);
        }

        System.out.println(linkedBlockingQueue.size());
    }

}

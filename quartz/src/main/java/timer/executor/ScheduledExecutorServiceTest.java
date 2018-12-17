package timer.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * corePoolSize:留存在线程池中的线程数量，即使这些线程是空闲的
 * maximumPoolSize:允许存在于线程池中的最大的线程数量
 * keepAliveTime:
 *
 * workQueue: 多余的Task存储在队列中，Runable接口类
 *
 * 拒绝策略
 *
 * AbortPolicy：抛出异常，RejectedExecutionException
 * CallerRunsPolicy：task任务直接调用run方法
 * DiscardPolicy：默默的丢弃任务
 * DiscardOldestPolicy:丢弃最老的消息，并重新执行
 *
 *
 *
 *
 * RUNNING:  Accept new tasks and process queued tasks
 * SHUTDOWN: Don't accept new tasks, but process queued tasks
 * STOP:     Don't accept new tasks, don't process queued tasks,
 *           and interrupt in-progress tasks
 * TIDYING:  All tasks have terminated, workerCount is zero,
 *           the thread transitioning to state TIDYING
 *           will run the terminated() hook method
 * ERMINATED: terminated() has completed
 */
public class ScheduledExecutorServiceTest {

    private static int count = 0;

    private static Executor executor = new ScheduledThreadPoolExecutor(10);

    public static void main(String[] args) {
        for (int i = 0;i < 100;i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ", count = " + count++);
                try {
                    Thread.sleep(10*1000);
                    System.out.println(Thread.currentThread().getName() + " 睡眠结束！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

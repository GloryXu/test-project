package thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class ExecutorSample {
    public static void main(String[] args) {
        Executors.newScheduledThreadPool(3);
        Executors.newFixedThreadPool(22);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(3);

        Executors.newCachedThreadPool();
        new LongAdder().add(1);
        new LongAdder().increment();
        new LongAdder().decrement();
    }
}

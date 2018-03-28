package condition;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionUseCase {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    @Test
    public void testCondition() throws InterruptedException {
        new Thread(() -> {
            try {
                conditionWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("等待10s");
        Thread.sleep(10000);

        new Thread(() -> {
            try {
                conditionSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(1);
            condition.await();
        } finally {
            System.out.println(2);
            lock.unlock();
        }
    }

    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(3);
            condition.signal();
        } finally {
            System.out.println(4);
            lock.unlock();
        }
    }
}

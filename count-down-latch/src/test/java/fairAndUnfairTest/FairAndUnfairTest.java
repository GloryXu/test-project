package fairAndUnfairTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnfairTest {
    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2(false);

    @Test
    public void fair() {
        testLock(fairLock);
    }

    @Test
    public void unfair() {
        testLock(unfairLock);
    }

    private void testLock(Lock lock) {
        // 启动5个Job（略）
        for (int i = 0; i < 1000; i++) {
            Job job = new Job(lock);
            job.setName(Integer.toString(i));
            job.start();
        }
    }

    private static class Job extends Thread {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        public void run() {
            lock.lock();
            // 连续2次打印当前的Thread和等待队列中的Thread（略）
            System.out.println("Lock by [" + Thread.currentThread().getName() + "], Waiting by [" + ((ReentrantLock2) lock).getQueuedThreads() + "]");
            System.out.println("Lock by [" + Thread.currentThread().getName() + "], Waiting by [" + ((ReentrantLock2) lock).getQueuedThreads() + "]");
            lock.unlock();
        }
    }

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}

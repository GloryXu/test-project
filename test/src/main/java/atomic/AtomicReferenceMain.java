package atomic;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xuguangrong
 * @description AtomicReference 测试方法
 * @date Created at 15:01 2019/6/7
 */
public class AtomicReferenceMain {

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Integer> count = new AtomicReference<>(0);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5, 10, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(800));

        final int[] count1 = {0};
        for (int i = 0;i<100;i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count.compareAndSet(count.get(), count.get() + 1);
//                count.getAndSet(count.get() + 1);
                count1[0]++;
            });
        }

        Thread.sleep(1000);
        System.out.println("count:"+count.get());
        System.out.println("count1[0]:"+count1[0]);
    }

}

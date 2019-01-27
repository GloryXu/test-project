package test.redsun.connect.pool;

import com.redsun.connect.pool.entity.TestEntity;
import com.redsun.connect.pool.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import test.redsun.base.Base;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author xuguangrong
 * @description 连接池性能测试类
 * @date Created at 15:19 2019/1/27
 */
public class ConnectPoolTest extends Base {

    @Autowired
    private TestService testService;

    @Test
    @Rollback
    @Transactional
    public void testTime() throws InterruptedException {
        int countThread = 100;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(countThread);
        if (executor.getPoolSize() < countThread) {
            Thread.sleep(10);
        }

        int[] counts = new int[]{10, 100, 1000, 5000};
        for (int count : counts) {
            long start = System.currentTimeMillis();

            CountDownLatch c = new CountDownLatch(count);
            for (int i = 0;i < count; i++) {
                executor.execute(() -> {
                    TestEntity testEntity = new TestEntity();
                    testEntity.setName(UUID.randomUUID().toString());
                    testService.save(testEntity);
                    int id = testEntity.getId();
                    testService.findEntity(id);
                    testService.delete(id);

                    c.countDown();
                });
            }
            c.await();

            System.out.printf("------------执行 %d 次花费 %d ms", count, (System.currentTimeMillis() - start));
            System.out.println();
        }
    }
}

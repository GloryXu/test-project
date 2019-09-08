package test.fuse.semaphore;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import fuse.semaphore.SemaphoreHystrix;
import fuse.semaphore.SemaphoreHystrixDifferCommandKey;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author xuguangrong
 * @description 信号量实现的熔断器测试类
 * @date Created at 20:24 2019/8/18
 */
public class SemaphoreHystrixDifferCommandKeyTest {

    private Logger log = Logger.getLogger("SemaphoreHystrixDifferCommandKeyTest");

    @Test
    public void testHystrix() throws InterruptedException {
        log.info("test start!");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            for (int i = 1;i <= 100;i++) {
                new SemaphoreHystrixDifferCommandKey(i, i % 2 == 0? "evenNumber":"oddNumber").execute();
                Thread.sleep(200);
            }
        } finally {
            context.shutdown();
        }
        log.info("test end!");
    }

}

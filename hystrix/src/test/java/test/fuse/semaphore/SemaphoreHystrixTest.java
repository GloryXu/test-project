package test.fuse.semaphore;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import fuse.semaphore.SemaphoreHystrix;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author xuguangrong
 * @description 信号量实现的熔断器测试类
 * @date Created at 20:24 2019/8/18
 */
public class SemaphoreHystrixTest {

    private Logger log = Logger.getLogger("SemaphoreHystrixTest");

    @Test
    public void testHystrix() throws InterruptedException {
        log.info("test start!");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            for (int i = 1;i<=20;i++) {
                new SemaphoreHystrix(i).execute();
                Thread.sleep(1000);
            }
        } finally {
            context.shutdown();
        }
        log.info("test end!");
    }

}

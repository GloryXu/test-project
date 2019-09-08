package test.fallback;

import com.netflix.hystrix.HystrixEventType;
import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.hystrix.HystrixRequestLog;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import fallback.CommandWithFallbackViaNetwork;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author xuguangrong
 * @description
 * @date Created at 16:24 2019/8/10
 */
public class UnitTest {
    private Logger log = Logger.getLogger("UnitTest");

    @Test
    public void test() {
        log.info("------------test starting!");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            Assert.assertEquals("hahh", new CommandWithFallbackViaNetwork(1).execute());
            HystrixInvokableInfo<?> command1 = HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().toArray(new HystrixInvokableInfo<?>[2])[0];
            assertEquals("GetValueCommand", command1.getCommandKey().name());
            assertEquals("RemoteServiceX", command1.getCommandGroup().name());
            assertTrue(command1.getExecutionEvents().contains(HystrixEventType.FAILURE));

            HystrixInvokableInfo<?> command2 = HystrixRequestLog.getCurrentRequest().getAllExecutedCommands().toArray(new HystrixInvokableInfo<?>[2])[1];
            assertEquals("GetValueFallbackCommand", command2.getCommandKey().name());
            assertEquals("RemoteServiceX", command2.getCommandGroup().name());
            assertTrue(command2.getExecutionEvents().contains(HystrixEventType.FAILURE));
        } finally {
            log.info("------------context shutdown!");
            context.shutdown();// 初始化之后必须要被调用，否则会引起内存泄露的问题
        }
    }

    @Test
    public void testCycle() {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            for (int i = 0;i<10;i++) {
                new CommandWithFallbackViaNetwork(1).execute();
            }
        } finally {
            context.shutdown();
        }
    }
}
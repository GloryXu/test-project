package fuse.semaphore;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuguangrong
 * @description 信号量实现的熔断
 * @date Created at 20:01 2019/8/18
 */
public class SemaphoreHystrix extends HystrixCommand<Integer> {

    private static final Logger log = Logger.getLogger(SemaphoreHystrix.class);

    private Integer number;

    // 应该会失败的数字
    private static List<Integer> failNumbers = new ArrayList<>();
    static {
        failNumbers.add(1);
        failNumbers.add(3);
        failNumbers.add(5);
        failNumbers.add(7);
        failNumbers.add(9);
    }

    public SemaphoreHystrix(Integer number) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CommandKey"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        // 使用信号量做资源隔离
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        // 至少有5个请求，熔断器才进行错误率的计算
                        .withCircuitBreakerRequestVolumeThreshold(5)
                        // 熔断器中断请求30s后会进入半打开状态,放部分流量过去重试
                        .withCircuitBreakerSleepWindowInMilliseconds(30000)
                        // 错误率达到50开启熔断保护
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        // 关闭缓存
                        .withRequestCacheEnabled(false)
                        // 关闭请求日志缓存
                        .withRequestLogEnabled(false)
                        .withExecutionTimeoutEnabled(true)));

        this.number = number;
    }

    @Override
    protected Integer run() throws Exception {
        log.warn("run execute!number = " + number);
        if (failNumbers.contains(number)) {
            log.error("run execute failed!number = " + number);
            throw new RuntimeException("invoke failed!");
        }
        return number;
    }

    @Override
    protected Integer getFallback() {
        log.warn("failback execute!number = " + number);
        return 0;
    }
}

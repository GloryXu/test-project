package fuse.semaphore;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuguangrong
 * @description 信号量实现的熔断
 * @date Created at 20:01 2019/8/18
 */
public class SemaphoreHystrixDifferCommandKey extends HystrixCommand<Integer> {

    private static final Logger log = Logger.getLogger(SemaphoreHystrixDifferCommandKey.class);

    private Integer number;

    private Integer runNumber = 1;
    private Integer failbackNumber = 1;

    // 应该会失败的数字
    private static List<Integer> failNumbers = new ArrayList<>();
    static {
        failNumbers.add(1);
        failNumbers.add(7);
        failNumbers.add(15);
        failNumbers.add(9);
        failNumbers.add(11);

        failNumbers.add(10);
        failNumbers.add(12);
        failNumbers.add(14);
        failNumbers.add(16);
    }

    public SemaphoreHystrixDifferCommandKey(Integer number, String commandKey) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandGroupKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
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
        runNumber++;

//        log.warn("run execute!number = " + number);
        if (failNumbers.contains(number)) {
//            log.error("run execute failed!number = " + number);
            throw new RuntimeException("invoke failed!");
        }
        log.warn("正常执行结束啦!number = " + number);
        return number;
    }

    @Override
    protected Integer getFallback() {
        if (failbackNumber == runNumber) {
            log.warn("直接降级啦!number = " + number);
        } else {
            log.warn("执行后降级啦!number = " + number);
        }
        return 0;
    }
}

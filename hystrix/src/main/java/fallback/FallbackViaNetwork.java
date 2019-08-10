package fallback;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.apache.log4j.Logger;

/**
 * @author xuguangrong
 * @description
 * @date Created at 16:24 2019/8/10
 */
public class FallbackViaNetwork extends HystrixCommand<String> {
    private final int id;
    private Logger log = Logger.getLogger("FallbackViaNetwork");

    public FallbackViaNetwork(int id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueFallbackCommand"))
                // use a different threadpool for the fallback command
                // so saturating the RemoteServiceX pool won't prevent
                // fallbacks from executing
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("RemoteServiceXFallback")));
        this.id = id;
    }

    @Override
    protected String run() {
//            MemCacheClient.getValue(id);
        log.info("FallbackViaNetwork run execute!");
        throw new RuntimeException("the fallback also failed");
    }

    @Override
    protected String getFallback() {
        // the fallback also failed
        // so this fallback-of-a-fallback will
        // fail silently and return null
        log.info("FallbackViaNetwork getFallback execute!");
        return "hahh";
    }
}

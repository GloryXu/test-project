package fallback;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.apache.log4j.Logger;

/**
 * @author xuguangrong
 * @description
 * @date Created at 15:18 2019/8/10
 */
public class CommandWithFallbackViaNetwork extends HystrixCommand<String> {
    private Logger log = Logger.getLogger("CommandWithFallbackViaNetwork");

    private final int id;

    public CommandWithFallbackViaNetwork(int id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetValueCommand")));
        this.id = id;
    }

    @Override
    protected String run() {
        log.info("run execute!");
        throw new RuntimeException("force failure for example");
    }

    @Override
    protected String getFallback() {
        log.info("getFallback execute!");
        return new FallbackViaNetwork(id).execute();
    }
}
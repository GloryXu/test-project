package distributed.lock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author glory
 * @description redis实现的分布式锁 - 续期
 * @date Created at 下午10:26 19-3-19
 */
public class DemoMain {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("anyLock");

        lock.lock();

        lock.unlock();
    }

}

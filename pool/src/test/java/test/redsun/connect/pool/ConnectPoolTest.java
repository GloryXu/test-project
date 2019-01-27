package test.redsun.connect.pool;

import com.redsun.connect.pool.entity.TestEntity;
import com.redsun.connect.pool.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import test.redsun.base.Base;

import java.util.UUID;

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
    public void testTime() {
        long start = System.currentTimeMillis();
        int count = 2;
        for (int i = 0;i < count;i++) {
            TestEntity testEntity = new TestEntity();
            testEntity.setName(UUID.randomUUID().toString());
            testService.save(testEntity);
//            testService.findEntity(i);
        }


        System.out.printf("------------执行 %d 次花费 %d ms", count, (System.currentTimeMillis() - start));
    }
}

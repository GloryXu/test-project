package test.multi.db;

import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import multi.db.entity.Test1;
import multi.db.service.Test1Service;
import multi.db.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author xuguangrong
 * @description 多数据源切换测试类
 * @date Created at 15:04 2019/1/17
 */
@Slf4j
public class MultiDbExchangeTest extends BaseTest {

    @Autowired
    private TestService testService;

    @Autowired
    private Test1Service test1Service;

    @Test
    public void testQuery() {
        multi.db.entity.Test entity = testService.testMethod(1);
        log.info(entity == null ? "null" : entity.toString());
    }

    @Test
    public void testInsert() {
        log.info("dsfsdf{}", "ddddddd");
        Test1 test1 = new Test1();
        test1.setName(UUID.randomUUID().toString());

        int test1Id = test1Service.save(test1);

        multi.db.entity.Test test = new multi.db.entity.Test();
        test.setName(UUID.randomUUID().toString());
        int testID = testService.save(test);

        //  查询
        Test1 test11Query = test1Service.test1Query(test1Id);
        log.info(test11Query == null ? "null" : test11Query.toString());

        multi.db.entity.Test testQuery = testService.testMethod(testID);
        log.info(testQuery == null ? "null" : testQuery.toString());
    }
}

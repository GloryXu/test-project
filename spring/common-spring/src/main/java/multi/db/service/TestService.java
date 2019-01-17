package multi.db.service;

import multi.db.dao.TestMapper;
import multi.db.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuguangrong
 * @description test service
 * @date Created at 14:42 2019/1/17
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public Test testMethod(int id) {
        return testMapper.selectByPrimaryKey(id);
    }

    public int save(Test test) {
        return testMapper.insert(test);
    }
}

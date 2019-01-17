package multi.db.service;

import multi.db.dao.Test1Mapper;
import multi.db.dao.TestMapper;
import multi.db.entity.Test1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuguangrong
 * @description test service
 * @date Created at 14:42 2019/1/17
 */
@Service
public class Test1Service {

    @Autowired
    private Test1Mapper test1Mapper;

    public Test1 test1Query(int id) {
        return test1Mapper.selectByPrimaryKey(id);
    }

    public int save(Test1 test1) {
        return test1Mapper.insert(test1);
    }
}

package com.redsun.connect.pool.service;

import com.redsun.connect.pool.dao.TestMapper;
import com.redsun.connect.pool.entity.TestEntity;
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

    public TestEntity findEntity(int id) {
        return testMapper.selectByPrimaryKey(id);
    }

    public int save(TestEntity test) {
        return testMapper.insert(test);
    }
}

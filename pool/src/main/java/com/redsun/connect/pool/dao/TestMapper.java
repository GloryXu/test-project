package com.redsun.connect.pool.dao;

import com.redsun.connect.pool.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestEntity record);

    TestEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestEntity record);

    int updateByPrimaryKey(TestEntity record);
}
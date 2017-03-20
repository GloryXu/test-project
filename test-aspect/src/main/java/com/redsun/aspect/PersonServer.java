package com.redsun.aspect;

/**
 * Created by xugr on 2017/3/20.
 */
public interface PersonServer {
    void save(String name);

    void update(String name, Integer id);

    String getPersonName(Integer id);
}

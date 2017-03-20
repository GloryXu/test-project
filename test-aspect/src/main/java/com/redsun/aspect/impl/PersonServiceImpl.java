package com.redsun.aspect.impl;

import com.redsun.aspect.PersonServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by xugr on 2017/3/20.
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonServer {

    public void save(String name) {
        log.info("this is save method!");
    }

    public void update(String name, Integer id) {
        log.info("this is update method!");
    }

    public String getPersonName(Integer id) {
        return "getPersonName";
    }
}

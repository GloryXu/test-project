package com.redsun;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SuperClass {

    @Autowired
    protected AutowireClass autowireClass;

    public void printLog() {
        log.info("SuperClass printLog ,autowireClass = {}", autowireClass);
    }
}

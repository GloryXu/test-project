package com.redsun.sub;

import com.redsun.SuperClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubClass extends SuperClass {

    public void printLog() {
        log.info("SubClass printLog , autowireClass = {}", autowireClass);
    }

    public SubClass() {
        log.info("non-param constructor.");
//        throw new RuntimeException("test exception");
        log.info("{}", new RuntimeException("test exception"));
    }

}

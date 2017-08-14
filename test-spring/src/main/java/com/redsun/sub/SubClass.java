package com.redsun.sub;

import com.redsun.AutowireClass;
import com.redsun.SuperClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubClass extends SuperClass {

    public void printLog() {
        log.info("SubClass printLog , autowireClass = {}", autowireClass);
    }
}

package com.redsun;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutowireClass {
    public void printLog() {
        log.info("AutowireClass printLog");
    }
}

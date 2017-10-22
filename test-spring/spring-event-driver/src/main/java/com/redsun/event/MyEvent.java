package com.redsun.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class MyEvent extends ApplicationEvent{

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyEvent(Object source) {
        super(source);
        log.info("this is my event!");
    }

    public void print(){
        log.info("hello spring event[MyEvent]");
    }
}

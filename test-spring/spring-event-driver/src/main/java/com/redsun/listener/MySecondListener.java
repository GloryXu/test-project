package com.redsun.listener;

import com.redsun.event.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MySecondListener implements ApplicationListener<MyEvent>{


    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(MyEvent event) {
        log.info("into My second Listener");
        event.print();
    }
}

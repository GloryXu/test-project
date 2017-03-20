package com.redsun.quartz.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by xugr on 2017/2/17.
 */

@Slf4j
@Service
public class BeanA {

    public void work (String test, String test11) {
        Date date = new Date();

        log.info("-----------------------{}", date);

        log.info("------------test = {}, test11 = {}", test,test11);
    }

}

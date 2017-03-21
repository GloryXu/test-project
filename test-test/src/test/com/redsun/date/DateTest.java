package com.redsun.date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Created by xugr on 2017/3/21.
 */
@Slf4j
public class DateTest {

    @Test
    public void testAddDay() {
        Date date = DateUtils.addDays(new Date(), 1);
        log.info("------date = {}", date);
    }
}

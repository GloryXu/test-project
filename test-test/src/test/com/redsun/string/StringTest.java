package com.redsun.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by DELL on 2017/6/27.
 */
@Slf4j
public class StringTest {

    @Test
    public void testStringBuffer() {
        StringBuffer sb = new StringBuffer();
        log.info("------reslt = {}", "".equals(sb.toString()));
    }
}

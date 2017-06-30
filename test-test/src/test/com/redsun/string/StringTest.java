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

    @Test
    public void testSwitch() {
        switch ("xugr") {
            case "1xugr1" :
                log.info("xugr1 xugr1");
                break;
            case "xugr" :
                log.info("xugr xugr");
                break;
            case "1xugr2" :
                log.info("xugr2 xugr2");
                break;
            default:
                log.info("default");
                break;
        }
    }

    @Test
    public void testSwitchNumBer() {
        switch (2) {
            case 1 :
                log.info("xugr xugr");
            case 2 :
                log.info("xugr1 xugr1");
            case 4 :
                log.info("xugr2 xugr2");
            default:
                log.info("default");
        }
    }
}

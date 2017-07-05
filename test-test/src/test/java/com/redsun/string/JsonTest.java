package com.redsun.string;

import com.google.gson.Gson;
import com.redsun.domain.TestPDU;
import lombok.extern.slf4j.Slf4j;
import net.sf.oval.Validator;
import org.junit.Test;

/**
 * Created by DELL on 2017/7/4.
 */
@Slf4j
public class JsonTest {

    @Test
    public void testConvert() {
        String str = "{\"test1\":\"\",\"test2\":\"value2\"}";

        Gson gson = new Gson();
        TestPDU testPDU = gson.fromJson(str, TestPDU.class);

        log.info("validete = {}", new Validator().validate(testPDU));
        log.info("result = {}", testPDU);
    }
}

package com.redsun.string;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.Map;
import java.util.regex.Pattern;

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

    @Test
    public void testStr() {
        String str1 = "xugr" + "xugr";
        String str2 = "xugrxugr";

        String str3 = str2 + "";

        log.info("str1.equals(str2) result = {}", str1.equals(str2));
        log.info("str1 == str2, result = {}", str1 == str2);
        log.info("str3 == str1 result = {}", str3 == str1);
    }

    @Test
    public void testJson() {
        Map<String,String> map = Maps.newHashMap();

        map.put("str1", "str11");
        map.put("str2", null);
        map.put("str3", "");

        Gson gson = new Gson();

        log.info("result = {}", gson.toJson(map));
    }

    @Test
    public void testNumStr() {
        String str00 = "00";
        String str99 = "99";
        String str = "01";

        log.info("{}",NumberUtils.isDigits(str));
        log.info("{}",NumberUtils.isDigits(str00));
        log.info("{}",NumberUtils.isDigits(str99));
        log.info("{}", NumberUtils.isDigits("aa"));
    }

    @Test
    public void testFor() {
        String[] strs = {"111","222","333"};
        for (String str : strs) {
            if ("222".equals(str)) {
                log.info("{}",str);
                break;
            }
            log.info("{}",str);
        }
    }

    @Test
    public void testPattern() {
        String regex = "(10|20|30)&(aa|bb)&$";
        Pattern pattern = Pattern.compile(regex);

        String str = "10a";
        String str1 = "10&aa&";
        String str2 = "20&bb&";
        String str3 = "30&bb&";

        log.info("result = {}",pattern.matcher(str).matches());
        log.info("result = {}",pattern.matcher(str1).matches());
        log.info("result = {}",pattern.matcher(str2).matches());
        log.info("result = {}",pattern.matcher(str3).matches());
    }

    @Test
    public void printStr() {
        String str = "*|*|*|*|*";
        String str1 = "s,df,s,d";

        log.info("str = {}", str.replace("|", ","));
        log.info("str1 = {}", str1.replace(",", "|"));
    }
}

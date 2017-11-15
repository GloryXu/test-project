package test.redsun.string;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.Base64;
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
            case "1xugr1":
                log.info("xugr1 xugr1");
                break;
            case "xugr":
                log.info("xugr xugr");
                break;
            case "1xugr2":
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
            case 1:
                log.info("xugr xugr");
            case 2:
                log.info("xugr1 xugr1");
            case 4:
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
        Map<String, String> map = Maps.newHashMap();

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

        log.info("{}", NumberUtils.isDigits(str));
        log.info("{}", NumberUtils.isDigits(str00));
        log.info("{}", NumberUtils.isDigits(str99));
        log.info("{}", NumberUtils.isDigits("aa"));
    }

    @Test
    public void testFor() {
        String[] strs = {"111", "222", "333"};
        for (String str : strs) {
            if ("222".equals(str)) {
                log.info("{}", str);
                break;
            }
            log.info("{}", str);
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

        log.info("result = {}", pattern.matcher(str).matches());
        log.info("result = {}", pattern.matcher(str1).matches());
        log.info("result = {}", pattern.matcher(str2).matches());
        log.info("result = {}", pattern.matcher(str3).matches());
    }

    @Test
    public void printStr() {
        String str = "*|*dd|*dd|dd*|*dd";
        String str1 = "s,df,s,d";

        log.info("str = {}", str.replace("|", ","));
        log.info("str1 = {}", str1.replace(",", "|"));

        log.info("{}", str.split("\\|").length);
    }

    @Test
    public void strToHex() {
        String str = "allinpay";
        System.out.println(new String(Hex.encodeHex(str.getBytes())).toUpperCase());
    }

    @Test
    public void strIndex() {
        String respStr = "<?xml?><root>dfsfsdf</root>";
        System.out.println(respStr.substring(respStr.indexOf("root") - 1, respStr.lastIndexOf("root") + 5));
        System.out.println(respStr.lastIndexOf("root"));
    }

    @Test
    public void baseToHex() {
        String base64 = "AQAG/1Xtj+UaOGJj9wBW9N0tiE7rJoNHfjkuIWyTBMs413fSR0d7COP4+K4VuNN59lvVi+LH0y9gIcS0dxWiiDvAWtDtYEDa/KBf1CCrVf6BkNduZskQ99z7rC5S6NPygbXSqvPoO9DC53dK6QEZU+7dyQFaVW/resq51SqCzst7JRM313JRHIkW9TfWIHMvqwug5odUJtjNkeCfw6+oyj6HAtqKaug9IQjeEwBOiS3mAR/i8Axxoy3W9PtxKcnxLq97enUpaONoTJrI+enPRxc6d7TVGNnG9TO+TaNj55jVSDfRjchBZksj+D8nwUAvmD0pUMJlucgTN102yLAhYZ+uuQkvbWJDA8cOq9K0e7FHj1esgwo94Lw41wHJ5skxPgc=";
        System.out.println(Hex.encodeHex(Base64.getDecoder().decode(base64), false));
    }
}

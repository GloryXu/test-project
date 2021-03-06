package string;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import util.PrintUtils;

import java.util.Base64;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by DELL on 2017/6/27.
 */
@Slf4j
public class StringTest {

    /**
     * 分隔符
     */
    public static final String DELIMITER = ";";

    /**
     * KV分隔符
     */
    public static final String KV_DELIMITER = "=";

    /**
     * 通过feature字符串构建featureMap
     *
     * @param features features字符串
     * @return featureMap
     */
    public static Map<String, String> buildFeatureMap(String features) {
        if (StringUtils.isBlank(features)) {
            return Maps.newHashMap();
        }
        Map<String, String> featureMap = Maps.newHashMap();
        String[] featureArray = features.split(DELIMITER);
        if (featureArray.length > 0) {
            for (String feature : featureArray) {
                String[] featureArr = feature.split(KV_DELIMITER);
                if (featureArr.length > 1) {
                    featureMap.put(featureArr[0], featureArr[1]);
                }
            }
        }
        return featureMap;
    }

    /**
     * 测试差异
     */
    @Test
    public void testDiff() {
        String old =     "whcGreyFlag=111;industryServiceId=5000000000059;cjOutboundFlag=Y;dn=null;sAreaName=增城区;receiverNick=xyz天使与恶魔;noMultiWave=true;tmsServiceCode=DISTRIBUTOR_525912;rCountryName=CN;unionDelivery=2021/03/07;whSinkStat=-1;supportJPKX=1;afOrderPrice=null;priorityConsign=false;sName=梁淑华;sTownName=新塘镇;sProvinceName=广东省;sMobile=18825192492;tmsServiceName=芝麻开门;isStrongMode=true;sPhone=18825192492;sCityName=广州市;sZipCode=511340;firstRdcType=DISTRIBUTION_DIRECTION;sAddress=永宁街沙宁路创业大道161号菜鸟网络（6号库1楼02号门）;orderFlag=34;orderPayTime=2021-03-05 13:51:15;first_rdc_name=N02;deliveryServiceType=std;sCountryName=CN;weight=9000;senderDivisionId=440183101;buyerDivisionId=441901006;";
        String newStr =  "whcGreyFlag=111;buyerDivisionId=445121;cjOutboundFlag=Y;sAreaName=清城区;receiverNick=null;noMultiWave=true;rCountryName=CN;afOrderPrice=null;priorityConsign=false;weight=187;isStrongMode=false;sZipCode=510000;senderDivisionId=441802103;industryServiceId=5000000000013;tmsServiceCode=DISTRIBUTOR_11903420;unionDelivery=2021/02/23;whSinkStat=-1;sName=杨曼华;sTownName=源潭镇;postFee=0;sProvinceName=广东省;sMobile=13822077825;tmsServiceName=广东EMS-省内;sCityName=清远市;sAddress=广东省清远市清城区G0423(乐广高速)中国南部物流枢纽园区3号库;CODamount=0;orderFlag=34;tn=null;orderPayTime=2021-02-19 17:21:37;deliveryServiceType=std;sCountryName=CN";
        Map<String, String> oldMap = buildFeatureMap(old);
        Map<String, String> newStrMap = buildFeatureMap(newStr);

        Set<String> newLess = new HashSet<>();
        oldMap.entrySet().stream().forEach(entry -> {
            if(!newStrMap.containsKey(entry.getKey())){
                newLess.add(entry.getKey());
            }
        });
        System.out.println(newLess);
    }

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

    @Test
    public void testStringAppend() {
        /*Integer tem = null;
        System.out.println("" + tem);
        StringBuffer sb = new StringBuffer("");
        sb.append(tem);
        System.out.println("sb="+sb);
        System.out.println(String.valueOf(tem));
        System.out.println(Integer.toString(tem));*/

        String aa = "aa";
        String result = "";
        long start = System.currentTimeMillis();
        for (int i = 0;i < 10000;i++) {
            result += aa + i;
        }
        System.out.println("字符串追加耗时：" + (System.currentTimeMillis() - start));
        System.out.println(result);


        start = System.currentTimeMillis();
        StringBuilder sbResult = new StringBuilder();
        for (int i = 0;i < 10000;i++) {
            sbResult.append(aa).append(i);
        }
        System.out.println("StringBuilder追加耗时：" + (System.currentTimeMillis() - start));
        System.out.println(sbResult.toString());

        start = System.currentTimeMillis();
        String resultFormat = "";
        for (int i = 0;i < 10000;i++) {
            resultFormat += String.format("%s%d", aa, i);
        }
        System.out.println("StringFormat追加耗时：" + (System.currentTimeMillis() - start));
        System.out.println(resultFormat);
    }

    @Test
    public void testStringA() {
        String str = "333%#%222";
        System.out.println(PrintUtils.printArr(str.split("%#%")));

        String str1 = "1121212#@__@#333333";
        System.out.println(PrintUtils.printArr(str1.split("#@__@#")));
    }

    @Test
    public void testIntern() {
        String a = new String("a");
        String aa = new String("a");
        System.out.println(a ==aa);

        String a1 = a.intern();
        String aa1 = aa.intern();
        System.out.println(a1 == aa1);
    }

    @Test
    public void testHashCode() {
        String str1 = "1";
        String str2 = "2";
        char chr = '1';
        System.out.println(chr);
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
    }

    @Test
    public void testInstansOf () {
        Object obj = null;
        System.out.println(obj instanceof Map);
    }

}

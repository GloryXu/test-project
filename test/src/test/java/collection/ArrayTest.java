package collection;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.junit.Test;

public class ArrayTest {
    public static void main(String[] args) {
        // 采用静态初始化方式初始化第一个数组
        String[] books = new String[]{
                "疯狂Java讲义",
                "轻量级Java EE企业应用实战",
                "疯狂Ajax讲义",
                "疯狂XML讲义"
        };
        // 采用静态初始化的简化形式初始化第二个数组
        String[] names = {
                "孙悟空",
                "猪八戒",
                "白骨精"
        };
        // 采用动态初始化的语法初始化第三个数组
        String[] strArr = new String[5];
        // 访问3个数组的长度
        System.out.println("第1个数组的长度：" + books.length);
        System.out.println("第2个数组的长度：" + names.length);
        System.out.println("第3个数组的长度：" + strArr.length);
    }

    @Test
    public void testBase64() throws Exception{
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><idcardType>01</idcardType><acctNo>12345643434568</acctNo><accessTraceDatetime>1213152644</accessTraceDatetime><acctCat>10</acctCat><idcardNo>345432345654</idcardNo><productId>9999</productId><issuerId>03080000</issuerId><phoneNo>15517509123</phoneNo><acctName>要发</acctName><accessTraceNo>789005</accessTraceNo><reserve>测试签约申请</reserve></request>";
        System.out.println(new String(Base64.encodeBase64(xml.getBytes("utf-8"))));
    }

    @Test
    public void testBase64Decode() throws Exception {
        String decodedStr = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48UkVTUE9OU0U+PGlkY2FyZFR5cGU+MDE8L2lkY2FyZFR5cGU+PGFjY3RObz4xMjM0NTY0MzQzNDU2ODwvYWNjdE5vPjxhY2Nlc3NUcmFjZURhdGV0aW1lPjEyMTMxNTI2NDQ8L2FjY2Vzc1RyYWNlRGF0ZXRpbWU+PFJFVF9NU0c+5Lqk5piT5oiQ5YqfPC9SRVRfTVNHPjxhY2N0Q2F0PjEwPC9hY2N0Q2F0PjxpZGNhcmRObz4zNDU0MzIzNDU2NTQ8L2lkY2FyZE5vPjxwcm9kdWN0SWQ+OTk5OTwvcHJvZHVjdElkPjxpc3N1ZXJJZD4wMzA4MDAwMDwvaXNzdWVySWQ+PHBob25lTm8+MTU1MTc1MDkxMjM8L3Bob25lTm8+PGFjY3ROYW1lPuimgeWPkTwvYWNjdE5hbWU+PGFjY2Vzc1RyYWNlTm8+Nzg5MDA1PC9hY2Nlc3NUcmFjZU5vPjxyZXNlcnZlPua1i+ivleetvue6pueUs+ivtzwvcmVzZXJ2ZT48UkVUX0NPREU+MDA8L1JFVF9DT0RFPjwvUkVTUE9OU0U+";
        System.out.println(new String(Base64.decodeBase64(decodedStr.getBytes())));
    }

    @Test
    public void testInt() {
        int a = 5;
        System.out.println(a / 2);
        System.out.println(a >> 2);
    }

    @Test
    public void testStringParse() {
        String range = "A1:AA1";


        String startString = range.split(":")[0];
        String endString = range.split(":")[1];

        String start = startString.substring(0, startString.length() - 1);
        String end = endString.substring(0, endString.length() - 1);

        for (int startTemp = start.charAt(0);;startTemp++) {
            if () {

            }
        }
    }
}

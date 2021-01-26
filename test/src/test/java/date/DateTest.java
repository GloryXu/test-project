package date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xugr on 2017/3/21.
 */
@Slf4j
public class DateTest {

    public static void main(String[] args) throws ParseException {
//        @-$$$&$%^^^ 年 &^^ 月 ^& 日 %^^ 时 &*%^^^ 分 *%^ 秒

        Date date = new SimpleDateFormat("yyyy年MM月hh日 HH时mm分ss秒").parse("4398年12月9日 07时48分56秒");
        System.out.println(date.getYear() + 1900);
        System.out.println(wathinsDateConverter(date));
    }

    /**
     * ^	1
     * %	5
     * &	10
     * *	50
     * $	100
     * #	500
     * @	1000
     * -	5000
     * @param earthDate
     * @return
     */
    public static String wathinsDateConverter(Date earthDate) {

        long sec = 56;
        long min = 48;
        long hour = 07;
        long day = 9;
        long month = 12;
        long year = 4398;

        return getTime(year) + "年" + getTime(month) + "月" + getTime(day) + "日 " + getTime(hour) + "时" + getTime(min) + "分" + getTime(sec) + "秒";
    }

    private static String getTime(long num) {
        Map<String, Long> numMap = new HashMap<>();
        numMap.put("^", 1L);
        numMap.put("%", 5L);
        numMap.put("&", 10L);
        numMap.put("*", 50L);
        numMap.put("$", 100L);
        numMap.put("#", 500L);
        numMap.put("@", 1000L);
        numMap.put("-", 5000L);

        String[] chars = new String[8];
        chars[7] = "^";
        chars[6] = "%";
        chars[5] = "&";
        chars[4] = "*";
        chars[3] = "$";
        chars[2] = "#";
        chars[1] = "@";
        chars[0] = "-";

        String result = "";

        long temp = num;
        int i = 0;
        long aa = 0;// 余数
        long bb = 0;// 倍数
        while (true) {
            String char1 = chars[i];
            aa = temp % numMap.get(char1);
            bb = temp / numMap.get(char1);
            if ( aa != temp) {
                if (bb > 3) {
                    if (StringUtils.isEmpty(result)) {
                        result += chars[i] + chars[i-1];
                    } else {
                        String lastChar = result.substring(result.length() - 1, result.length());
                        if (lastChar.equals(chars[i-1]) && ("%".equals(chars[i-1]) || "*".equals(chars[i-1]) || "#".equals(chars[i-1]))) {
                            result = result.substring(0, result.length() - 1) + chars[i] + chars[i-2];
                        } else {
                            result += chars[i] + chars[i-1];
                        }
                    }
                    temp = temp - bb*numMap.get(char1);
                } else {
                    temp = aa;
                    for (int j = 0;j<bb;j++) {
                        result += char1;
                    }
                }
            }
            i++;
            if (i>7) {
                break;
            }
        }


        return result;
    }

    @Test
    public void testLastWeek() throws ParseException {
        Date date =  new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));


        String date1Str = "2020-08-31";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse(date1Str);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);
        calendar1.setTime(date1);
        System.out.println(calendar1.get(Calendar.WEEK_OF_YEAR));
    }

    /**
     * 通过周几获取本周时间
     *
     * @param week
     * @param num   0：当前周，1：下一周，-1：上一周，以此类推
     * @return
     */
    private Date getTimeByWeek(String week, int num) {

        return new Date();
    }

    private String getWeek() {
        String week = "";
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            week = "周日";
        } else if (weekday == 2) {
            week = "周一";
        } else if (weekday == 3) {
            week = "周二";
        } else if (weekday == 4) {
            week = "周三";
        } else if (weekday == 5) {
            week = "周四";
        } else if (weekday == 6) {
            week = "周五";
        } else if (weekday == 7) {
            week = "周六";
        }
        return week;
    }

    @Test
    public void testAddDay() {
        Date date = DateUtils.addDays(new Date(), 1);
        log.info("------date = {}", date);
    }

    @Test
    public void testConvertDateFormat () throws ParseException {
        String strDate = "2017.07.19 19:53:22";
        String format1 = "yyyy.MM.dd HH:mm:ss";

        Date date = DateUtils.parseDate(strDate, format1);
        log.info("------------date = {}", date);
    }

    @Test
    public void testConvertDateFormat_1() throws ParseException {
        String[] pattern = new String[]{"yyyyHHddHHmmss"};

        Date date = org.apache.commons.lang.time.DateUtils.parseDate("20170820202020", pattern);
        log.info("-------------------------------date = {}", date);
    }

    @Test
    public void testLongToDate() {
        long time = 1511336240696L;
        Date date = new Date(time);
        log.info("-----------------date:{}",date);
    }

    @Test
    public void testYandy() {
        //      String dateStr = "20180101";
        String dateStr = "2017-03-10";
//        System.out.println(parse("yyyy-MM-dd", dateStr, Locale.getDefault()));
        System.out.println(parse("yyyy-MM-dd", dateStr));
//        System.out.println(parse("YYYY-MM-dd", dateStr, Locale.getDefault()));
        System.out.println(parse("YYYY-MM-dd", dateStr));
    }

    @Test
    public void testYandy2() {
        Calendar calendar = Calendar.getInstance();
        // 2010-12-26
        calendar.set(2010, Calendar.DECEMBER, 26);
        Date strDate1 = calendar.getTime();
        System.out.println(strDate1);

        SimpleDateFormat f1 = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("Result for YYYY: " + f1.format(strDate1));
        SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Result for yyyy: " + f2.format(strDate1));
    }
    public static java.util.Date parse(String pattern, String strDateTime) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.parse(strDateTime);
        } catch (Exception e) {
            return null;
        }
    }

    public static java.util.Date parse(String pattern, String strDateTime, Locale locale) {
        if (strDateTime == null || pattern == null)
            return null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
            formatter.setLenient(false);
            return formatter.parse(strDateTime);
        } catch (Exception e) {
            return null;
        }
    }

}

package date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

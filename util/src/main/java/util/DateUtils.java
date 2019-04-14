package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xuguangrong
 * @description 日期util
 * @date Created at 16:43 2019/4/14
 */
public class DateUtils {

    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String now() {
        return new SimpleDateFormat(DATE_FORMAT).format(new Date());
    }

}

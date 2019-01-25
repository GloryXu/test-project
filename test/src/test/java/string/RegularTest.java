package string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author xuguangrong
 * @description 正则相关测试
 * @date Created at 15:18 2019/1/25
 */
@Slf4j
public class RegularTest {

    /**
     * 字母开头且只能包含字母、数字和下划线
     */
    @Test
    public void test1() {
        String str = "你好";
        String str1 = "_sdjsd33";
        String str2 = "7979879";
        String str3 = "sdfs_sfsd9879";
        String regex = "^[a-zA-Z][a-zA-Z0-9_]*$";
        log.info(String.valueOf(Pattern.matches(regex, str)));
        log.info(String.valueOf(Pattern.matches(regex, str1)));
        log.info(String.valueOf(Pattern.matches(regex, str2)));
        log.info(String.valueOf(Pattern.matches(regex, str3)));
    }
}

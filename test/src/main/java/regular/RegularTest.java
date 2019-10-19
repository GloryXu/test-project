package regular;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuguangrong
 * @description 正则
 * @date Created at 10:28 2019/9/27
 */
public class RegularTest {

    @Test
    public void test1() {
        String str = "abc1abcabc";

        System.out.println(str.replaceAll("abc", "xxx"));
        System.out.println(str.replaceAll("^abc(.+?)", "xxx"));
    }

    @Test
    public void test2() {
        String str = "Awww.baidu.comB";

        System.out.println(str.replaceAll("A.*?B", "www.apizl.com"));
        System.out.println(str.replaceAll("(?<=A).*?(?=B)", "www.apizl.com"));
    }

    @Test
    public void test3() {
        String str1 = "var1*var2";
        String str2 = "var1var2+var1";
        String str3 = "哈哈哈《的胜多负少》对对对";

        System.out.println(str2.replaceAll("(?<=A).*?(?=B)|", "1"));
        System.out.println(str1.replaceAll("(?<=A).*?(?=B)", "2"));
        System.out.println(str3.replaceAll("(?<=\\《)[^\\》]+", "大大"));
    }

    @Test
    public void test4() {
        String s = "(((520+480)*38/10)/2*((520+480)*38/10)/2)) ";
        List<Char> list = new ArrayList<Char>();
        do {
            s = getString(s, list);
        } while (s != null && s.indexOf("(") != -1);
        for (Char c : list) {
            System.out.println(c.str.substring(c.startIndex, c.endIndex + 1));
        }
    }

    public static String getString(String str, List<Char> list) {
        char[] cs = str.toCharArray();
        boolean isStart = false;
        Char ch = new Char();
        ch.str = str;
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == '(') {
                if (!isStart) {
                    ch.startIndex = i;
                    isStart = true;
                }
                ch.layer++;
            } else if (c == ')' && ch.layer > 0) {
                ch.layer--;
                if (ch.layer == 0) {
                    ch.endIndex = i;
                    list.add(ch);
                    if (i != cs.length - 1) {
                        String last = str.substring(i + 1);
                        do {
                            last = getString(last, list);
                        } while (last != null && last.indexOf("(") != -1);
                    }
                    break;
                }
            }
        }
        if (ch.endIndex != 0) {
            return str.substring(ch.startIndex + 1, ch.endIndex);
        }
        return null;
    }

}

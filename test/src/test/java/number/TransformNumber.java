package number;

/**
 * @author xuguangrong
 * @description 数字转换成汉字
 * @date Created at 23:37 2019/3/17
 */
public class TransformNumber {
    public static void main(String[] args) {
        System.out.println(num2Ch("0"));
        System.out.println(num2Ch("1100"));
        System.out.println(num2Ch("1105"));
        System.out.println(num2Ch("100000000000"));
    }

    private static String num2Ch(String input) {
        input = Long.parseLong(input) + "";
        if (input.equals("0")) {
            return "零";
        }
        StringBuffer s;
        switch (4 - (input.length() % 4)) {
            case 1:
                s = new StringBuffer("0").append(input);
                break;
            case 2:
                s = new StringBuffer("00").append(input);
                break;
            case 3:
                s = new StringBuffer("000").append(input);
                break;
            default:
                s = new StringBuffer("").append(input);
        }
        String[] arr = new String[s.length() / 4];
        for (int i = 0; i < s.length(); i = i + 4) {
            if (i + 4 == s.length())
                arr[i / 4] = s.toString().substring(i);
            else
                arr[i / 4] = s.toString().substring(i, i + 4);
        }
        StringBuffer output = new StringBuffer("");
        switch (s.length() / 4) {
            case 3:
                output = output.append(trans(arr[s.length() / 4 - 3])).append("亿");
                break;
            case 2:
                if (Integer.parseInt(arr[s.length() / 4 - 2]) == 0 && Integer.parseInt(arr[s.length() / 4 - 3]) != 0)
                    output = output.append("零");
                else
                    output = output.append(trans(arr[s.length() / 4 - 2])).append("万");
                break;
            case 1:
                output = output.append(trans(arr[s.length() / 4 - 1]));
                break;
        }
        return output.toString();
    }

    // 四位数转换函数
    private static String trans(String string) {
        String s = Integer.parseInt(string) + "";
        StringBuffer output = new StringBuffer("");
        String[] arr = {"", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        if ((Integer.parseInt(s) + "").equals("")) {
            return "";
        } else {
            switch (s.length()) {
                case 4:
                    if (Integer.parseInt(string.charAt(0) + "") == 0) {
                        output = output.append("零");
                    } else {
                        output = output.append(arr[Integer.parseInt(s.charAt(0) + "")]).append("千");
                    }
                case 3:
                    if (Integer.parseInt(string.charAt(3) + "") != 0 && Integer.parseInt(string.charAt(1) + "") == 0 && Integer.parseInt(string.charAt(0) + "") != 0) {
                        output = output.append("零");
                    } else {
                        output = output.append(arr[Integer.parseInt(string.charAt(1) + "")]);
                    }
                    if (Integer.parseInt(string.charAt(1) + "") != 0) {
                        output = output.append("百");
                    }
                case 2:
                    if (Integer.parseInt(string.charAt(3) + "") != 0 && Integer.parseInt(string.charAt(2) + "") == 0 && Integer.parseInt(string.charAt(1) + "") != 0
                            && Integer.parseInt(string.charAt(0) + "") != 0) {
                        output = output.append("零");
                    } else {
                        output = output.append(arr[Integer.parseInt(string.charAt(2) + "")]);
                    }
                    if (Integer.parseInt(string.charAt(2) + "") != 0) {
                        output = output.append("十");
                    }
                case 1:
                    if (Integer.parseInt(string.charAt(3) + "") != 0) {
                        output = output.append(arr[Integer.parseInt(string.charAt(3) + "")]);
                    }
                    break;
            }
            return output.toString();
        }
    }
}

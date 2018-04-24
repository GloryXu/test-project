package util;

import java.util.List;

public class PrintUtils {
    /**
     * 打印数组
     *
     * @param arr
     * @return
     */
    public static String parintArr(Object[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append("[").append(arr[i].toString());
                continue;
            }
            if (i == arr.length - 1) {
                sb.append(",").append(arr[i].toString()).append("]");
                continue;
            }
            sb.append(",").append(arr[i].toString());
        }
        return sb.toString();
    }
    public static String parintIntArr(int[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append("[").append(arr[i]);
                continue;
            }
            if (i == arr.length - 1) {
                sb.append(",").append(arr[i]).append("]");
                continue;
            }
            sb.append(",").append(arr[i]);
        }
        return sb.toString();
    }

    public static String parintDoubleArr(double[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append("[").append(Double.toString(arr[i]));
                continue;
            }
            if (i == arr.length - 1) {
                sb.append(",").append(Double.toString(arr[i])).append("]");
                continue;
            }
            sb.append(",").append(Double.toString(arr[i]));
        }
        return sb.toString();
    }

    /**
     * 打印数组
     *
     * @param arr
     * @return
     */
    public static String parintArrayList(List arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.size(); i++) {
            if(arr.size() == 1) {
                sb.append("[").append(arr.get(i).toString()).append("]");
                continue;
            }
            if (i == 0) {
                sb.append("[").append(arr.get(i).toString());
                continue;
            }
            if (i == arr.size() - 1) {
                sb.append(",").append(arr.get(i).toString()).append("]");
                continue;
            }
            sb.append(",").append(arr.get(i).toString());
        }
        return sb.toString();
    }
}

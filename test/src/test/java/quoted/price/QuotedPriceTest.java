package quoted.price;

import org.junit.Test;
import util.PrintUtils;

import java.text.DecimalFormat;
import java.util.Random;

public class QuotedPriceTest {

    private double[] genPrice() {
        double[] prices = new double[31];
        double tem = 0;
        for(int i = 0;i<31;i++) {
            prices[i] = tem;
            tem += 0.002D;
        }
        return prices;
    }

    @Test
    public void test1000() {
        double[] prices = genPrice();
        System.out.println(PrintUtils.parintDoubleArr(prices));
        Random random = new Random();
        int[] counts = new int[prices.length];
        for(int j = 0;j<1000;j++) {
            double a = prices[random.nextInt(31)];
            double b = prices[random.nextInt(31)];
            double c = (a + b)/2;
            for(int ii = 0;ii<prices.length;ii++) {
                if (Math.abs(c - prices[ii]) < 0.001) {
                    counts[ii]++;
                }
            }
        }
        DecimalFormat df = new DecimalFormat( "0.000"); //设置double类型小数点后位数格式
        for (int jj = 0;jj<prices.length;jj++) {
            System.out.println(df.format(prices[jj]) + ":" + counts[jj]); //将输出2.10
        }
    }

    @Test
    public void test10000() {
        double[] prices = genPrice();
        System.out.println(PrintUtils.parintDoubleArr(prices));
        Random random = new Random();
        int[] counts = new int[prices.length];
        for(int j = 0;j<10000;j++) {
            double a = prices[random.nextInt(31)];
            double b = prices[random.nextInt(31)];
            double c = (a + b)/2;
            for(int ii = 0;ii<prices.length;ii++) {
                if (Math.abs(c - prices[ii]) < 0.001) {
                    counts[ii]++;
                }
            }
        }
        DecimalFormat df = new DecimalFormat( "0.000"); //设置double类型小数点后位数格式
        for (int jj = 0;jj<prices.length;jj++) {
            System.out.println(df.format(prices[jj]) + ":" + counts[jj]); //将输出2.10
        }
    }
}

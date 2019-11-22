package excel;

import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * @date 2018/7/27 16:41
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // excel
        InputStream is = new FileInputStream("C:\\Users\\xxxxx\\Desktop\\[2019-11-21 21_11_36]-.xls");
        ExcelReader excelReader = new ExcelReader(is);

        Map<String, String> umpCount = new HashMap();
        List<String[]> excelData = excelReader.getDataByIndex(0);
        for (int i = 1; i < excelData.size(); i++) {
            String[] dataTemp = excelData.get(i);
            umpCount.put(StringUtils.trim(dataTemp[0]), dataTemp[10]);
        }

        is.close();

        is = new FileInputStream("C:\\Users\\xxxxxx\\Desktop\\接口调用量统计.xlsx");
        excelReader = new ExcelReader(is);
        excelData = excelReader.getDataByIndex(0);
        for (int i = 0; i < excelData.size(); i++) {
            String[] dataTemp = excelData.get(i);
            String countStr = umpCount.get(StringUtils.trim(dataTemp[2]));
            System.out.println(countStr == null ? "" : countStr);
        }
    }

}
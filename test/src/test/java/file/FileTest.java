package file;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.util.StringUtils;
import util.DBUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
public class FileTest {

    @Test
    public void testDir() {
        String dir = "E:\\app\\ebank\\1275\\allinpay";

        File files = new File(dir);

        log.info("{}", files.list());
        log.info("{}", files.listFiles());
        log.info("{}", files.listFiles()[0].getName());
    }

    /**
     * 找出file1中有的file2中没有的ip信息
     */
    @Test
    public void testFindLessIp() throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\jd-doc\\jrm-doc\\02.性能相关\\runtime扩容后需要申请aks访问权限，如果IP少于申请的IP数，可用如下代码找出IP并让aks支持人员手动添加\\源IP信息.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("D:\\jd-doc\\jrm-doc\\02.性能相关\\runtime扩容后需要申请aks访问权限，如果IP少于申请的IP数，可用如下代码找出IP并让aks支持人员手动添加\\aks新添的ip信息.txt"));

        Set<String> oriIPs = new HashSet<>();
        while (true) {
            String line = br1.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            oriIPs.add(line);
        }
        System.out.println("源文件大小：" + oriIPs.size());

        Set<String> desIPs = new HashSet<>();
        while (true) {
            String line = br2.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            desIPs.add(line);
        }
        System.out.println("目标文件大小：" + desIPs.size());

        for (String ip : oriIPs) {
            if (!desIPs.contains(ip)) {
                System.out.println(ip);
            }
        }
    }

    /**
     * 找出file1中的ip信息
     */
    @Test
    public void testFindIpFromFile() throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\xuguangrong\\Desktop\\已上线.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\xuguangrong\\Desktop\\应该有的机器ip.txt"));

        Set<String> shangxian = new HashSet<>();
        while (true) {
            String line = br1.readLine();
            if (line == null) {
                break;
            }
            if (!StringUtils.isEmpty(line.trim()) && !line.contains("22000")) {
                shangxian.add(line);
            }
        }
        System.out.println("源文件大小：" + shangxian.size());

        Set<String> desIPs = new HashSet<>();
        while (true) {
            String line = br2.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            desIPs.add(line);
        }
        System.out.println("目标文件大小：" + desIPs.size());

        for (String ip : desIPs) {
            if (!shangxian.contains(ip)) {
                System.out.println(ip);
            }
        }
    }

    /**
     * 删除分库分表信息
     * @throws IOException
     */
    @Test
    public void testDelNum() throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\tmp\\20190506数据盘点\\原始表.txt"));

        while (true) {
            String line = br1.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            String[] comps = line.split("_");// 用_切分，获取最后一个信息
            String lastComp = comps[comps.length - 1];
            if (isNum(lastComp, 3)) {// 不是三位数字
                System.out.println(line.substring(0, line.length() - 4));
                continue;
            }
            System.out.println(line);
        }
    }

    /**
     * 检查是否数字
     * @param lastComp
     * @param size
     * @return
     */
    private boolean isNum(String lastComp, int size) {
        if(lastComp == null || lastComp.length() != 3) {
            return false;
        }
        Pattern pattern = Pattern.compile("\\d{"+size+"}");
        return pattern.matcher(lastComp).matches();
    }

    /**
     * 去重
     * @throws IOException
     */
    @Test
    public void testUnique() throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\tmp\\20190506数据盘点\\去除分库分表数字后.txt"));

        Set<String> result = new HashSet<>();

        while (true) {
            String line = br1.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            result.add(line);
        }

        for (String key : result) {
            System.out.println(key);
        }
    }

    @Test
    public void executeSql() throws IOException {
        String url = "jdbc:mysql://172.25.28.8:3306/smart_rm?characterEncoding=utf8&&zeroDateTimeBehavior=convertToNull&&allowMultiQueries=true";
        String user = "vvvv";
        String password = "1qavvzvvvvvv@WSvvvX";
        Set<String> results = DBUtils.executeSql(url, user, password, "select code from srm_app_table where del_status = 0");

        BufferedReader br1 = new BufferedReader(new FileReader("D:\\tmp\\20190506数据盘点\\去重后.txt"));
        while (true) {
            String line = br1.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            if (results.contains(line)) {
                System.out.println(line);
            }
        }
    }

    @Test
    public void readExcel() throws IOException, InvalidFormatException {
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\tmp\\20190506数据盘点\\在使用的.txt"));
        Set<String> inUsing = new HashSet<>();
        while (true) {
            String line = br1.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            inUsing.add(line);
        }

        String excelPath = "C:\\Users\\xuguangrong\\Desktop\\";
        FileInputStream fs = new FileInputStream(excelPath + "数字技术中心数据盘点确认与数据挂靠.xlsx");//获取excel
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet sheet = wb.getSheetAt(1);
        Iterator iterator = sheet.iterator();
        while(iterator.hasNext()) {
            Row row = (Row) iterator.next();
            String tableName = row.getCell(0).getStringCellValue();
            if (StringUtils.isEmpty(tableName)) {
                continue;
            }
            if (inUsing.contains(tableName) || (!inUsing.contains(tableName) && tableName.length() > 4 && inUsing.contains(tableName.substring(0, tableName.length() - 4)))) {
                System.out.println("正确");
            } else {
                System.out.println();
            }
        }
        wb.close();
        fs.close();
    }

    @Test
    public void testStringChar() {
        String str1 = "xxx";
        String str2 = "xxx_222";
        System.out.println(str2.substring(0, str2.length() - 4));
    }
}

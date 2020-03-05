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
import java.util.*;
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
     * 找出file1中有的file2中没有的ip信息
     */
    @Test
    public void testCreateTable() throws IOException {
//        BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\xuguangrong\\Desktop\\表名和描述.txt"));
        BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\xuguangrong\\Desktop\\逆向表名和描述.txt"));

        while (true) {
            String line = br1.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            String[] tableInfos = line.split("\t");
            String tableName = tableInfos[0];
            String desc = tableInfos[1];

            // 正向表结构
            /*String createTableTemplate = "CREATE TABLE `%s` (\n" +
                    "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',\n" +
                    "  `id_value` varchar(100) NOT NULL COMMENT '正向ID',\n" +
                    "  `data_src` varchar(30) NOT NULL COMMENT '正向ID渠道',\n" +
                    "  `unif_key` varchar(50) NOT NULL COMMENT 'key',\n" +
                    "  `data_src_score` varchar(5) NOT NULL COMMENT '渠道优先级',\n" +
                    "  `unif_key_conf` decimal(10,2) NOT NULL COMMENT 'KEY可信度',\n" +
                    "  `create_time` varchar(10) NOT NULL COMMENT '创建时间',\n" +
                    "  `update_time` varchar(10) NOT NULL COMMENT '更新时间',\n" +
                    "  `dt` varchar(16) NOT NULL COMMENT '日期分区',\n" +
                    "  `del_status` varchar(5) DEFAULT NULL COMMENT '删除标识',\n" +
                    "  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                    "  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `uniq_idx_key` (`id_value`,`data_src`,`unif_key`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='%s';";*/

            // 逆向表结构
            String createTableTemplate = "CREATE TABLE `%s` (\n" +
                    "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',\n" +
                    "  `unif_key` varchar(50) NOT NULL COMMENT 'key',\n" +
                    "  `id_value` varchar(100) NOT NULL COMMENT '逆向ID',\n" +
                    "  `data_src` varchar(30) NOT NULL COMMENT '逆向ID渠道',\n" +
                    "  `data_src_score` varchar(5) NOT NULL COMMENT '渠道优先级',\n" +
                    "  `unif_key_rel_conf` decimal(10,2) NOT NULL COMMENT '普通ID与统一ID关系可信度',\n" +
                    "  `id_actv_rate` decimal(10,2) NOT NULL COMMENT 'ID活跃度',\n" +
                    "  `create_time` varchar(10) NOT NULL COMMENT '创建时间',\n" +
                    "  `update_time` varchar(10) NOT NULL COMMENT '更新时间',\n" +
                    "  `dt` varchar(16) NOT NULL COMMENT '日期分区',\n" +
                    "  `del_status` varchar(5) DEFAULT NULL COMMENT '删除标识',\n" +
                    "  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                    "  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `uniq_idx_key` (`unif_key`,`id_value`,`data_src`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='%s';";

            System.out.println(String.format(createTableTemplate, tableName, desc));
            System.out.println();
            System.out.println();
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

    /**
     * 读取文件获取线程增加的10s，发起查询和返回的数量
     */
    @Test
    public void testQueryLineAndReturnLine() throws IOException {
//        发起查询次数327, 查询返回次数252
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\Downloads\\export\\Instances\\jrm-runtime\\server1\\logs\\线程增加的10s.txt"));

        // 正常情况
//        发起查询次数2070, 查询返回次数2070
//        BufferedReader br1 = new BufferedReader(new FileReader("D:\\Downloads\\export\\Instances\\jrm-runtime\\server1\\logs\\正常.txt"));
        // 发起查询次数746, 查询返回次数745
//        BufferedReader br1 = new BufferedReader(new FileReader("D:\\Downloads\\export\\Instances\\jrm-runtime\\server1\\logs\\正常1.txt"));

        int queryCount = 0;
        int retCount = 0;
        while (true) {
            String line = br1.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            if (line.contains("Exception") || line.contains("\tat ")) {
                continue;
            }
            if (line.contains("】查询") && line.contains("入参")) {
                queryCount++;
            }
            if (line.contains("】查询") && line.contains("返回结果")) {
                retCount++;
            }
        }

        System.out.println("发起查询次数" + queryCount + ", 查询返回次数" + retCount);
    }

    @Test
    public void testThreadCount() throws IOException {
//        BufferedReader br1 = new BufferedReader(new FileReader("D:\\Downloads\\export\\Instances\\jrm-runtime\\server1\\logs\\线程增加的10s.txt"));
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\Downloads\\export\\Instances\\jrm-runtime\\server1\\logs\\ok.txt"));
        Map<String, Integer> interCount = new HashMap<>();
        while (true) {
            String line = br1.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            if (line.contains("Exception") || line.contains("\tat ")) {
                continue;
            }

            String threadName = getThreadName(line);
            if (threadName == null) {
                continue;
            }

            Integer count = interCount.get(threadName);
            if (count == null) {
                interCount.put(threadName, 1);
            }else {
                interCount.put(threadName, ++count);
            }
        }

        ValueComparator bvc =  new ValueComparator(interCount);
        TreeMap<String,Integer> sorted_map = new TreeMap<>(bvc);
        sorted_map.putAll(interCount);

        System.out.println(sorted_map);
    }

    /**
     * 获取线程名称
     *
     * @param line
     * @return
     */
    private String getThreadName(String line) {
        try {
            int start = line.indexOf("[");

            int end = line.indexOf("]");
            String threadName1 = line.substring(start + 1, end);
//            return threadName1.substring(0, threadName1.lastIndexOf("-"));
            return threadName1;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(line);
            return null;
        }
    }

    @Test
    public void testExternalInterfaceCount() throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\Downloads\\export\\Instances\\jrm-runtime\\server1\\logs\\线程增加的10s.txt"));

        Map<String, Integer> interCount = new HashMap<>();


        while (true) {
            String line = br1.readLine();
            if (StringUtils.isEmpty(line)) {
                break;
            }
            if (line.contains("Exception") || line.contains("\tat ")) {
                continue;
            }
            if (line.contains("】查询") && line.contains("入参")) {
                String externalInterface = getInterface(line);
                Integer count = interCount.get(externalInterface);
                if (count == null) {
                    interCount.put(externalInterface, 1);
                }else {
                    interCount.put(externalInterface, ++count);
                }
            }
            if (line.contains("】查询") && line.contains("返回结果")) {
                String externalInterface = getInterface(line);
                Integer count = interCount.get(externalInterface);
                if (count == null) {
                    interCount.put(externalInterface, -1);
                } else {
                    interCount.put(externalInterface, --count);
                }
            }
        }

        ValueComparator bvc =  new ValueComparator(interCount);
        TreeMap<String,Integer> sorted_map = new TreeMap<>(bvc);
        sorted_map.putAll(interCount);

        System.out.println(sorted_map);
        System.out.println(sorted_map.size());

        for (Map.Entry<String, Integer> entry : sorted_map.entrySet()) {
            System.out.print("'" + entry.getKey()+"',");
        }
    }

    /**
     * 获取接口名
     *
     * @param line
     * @return
     */
    private String getInterface(String line) {
        int start1 = line.indexOf("【");
        int start = line.indexOf("【", start1 + 1);

        int end1 = line.indexOf("】");
        int end = line.indexOf("】", end1 + 1);
        return line.substring(start + 1, end);
    }


    class ValueComparator implements Comparator<String> {

        Map<String, Integer> base;
        //这里需要将要比较的map集合传进来
        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.
        //比较的时候，传入的两个参数应该是map的两个key，根据上面传入的要比较的集合base，可以获取到key对应的value，然后按照value进行比较
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }

}

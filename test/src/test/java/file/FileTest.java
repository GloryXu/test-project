package file;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

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
        BufferedReader br1 = new BufferedReader(new FileReader("D:\\jd-doc\\jrm-doc\\10.其他\\找缺少的IP\\源IP信息.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("D:\\jd-doc\\jrm-doc\\10.其他\\找缺少的IP\\aks新添的ip信息.txt"));

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
}

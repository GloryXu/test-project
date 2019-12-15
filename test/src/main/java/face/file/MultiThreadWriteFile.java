package face.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.*;

/**
 * @author xuguangrong
 * @description 多线程写文件
 * @date Created at 20:36 2019/12/15
 */
public class MultiThreadWriteFile extends Thread {

    private Map<String, List<String>> contents = null;// 总内容
    private String printContent = null;// 当前线程应该打印内容

    public static void main(String[] args) {
        Map<String, List<String>> contents = new TreeMap<>();
        List<String> file1Content = new LinkedList<>();
        file1Content.add("t1");
        file1Content.add("t2");
        file1Content.add("t3");
        file1Content.add("t1");
        file1Content.add("t2");
        contents.put("file1", file1Content);

        List<String> file2Content = new LinkedList<>();
        file1Content.add("t2");
        file1Content.add("t3");
        file1Content.add("t1");
        file1Content.add("t2");
        file1Content.add("t3");
        contents.put("file2", file2Content);


        List<String> file3Content = new LinkedList<>();
        file1Content.add("t3");
        file1Content.add("t1");
        file1Content.add("t2");
        file1Content.add("t3");
        file1Content.add("t1");
        contents.put("file3", file3Content);

        Thread t1 = new MultiThreadWriteFile(contents, "t1");
        Thread t2 = new MultiThreadWriteFile(contents, "t2");
        Thread t3 = new MultiThreadWriteFile(contents, "t3");

        t1.start();
        t2.start();
        t3.start();
    }

    public MultiThreadWriteFile(Map<String, List<String>> contents, String printContent) {
        this.printContent = printContent;
        this.contents = contents;
    }

    public void run() {
        File file1 = new File("D:/tmp/file1.txt");
        File file2 = new File("D:/tmp/file2.txt");
        File file3 = new File("D:/tmp/file3.txt");
        try {
            if (!file1.exists()) {
                file1.createNewFile();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (!file3.exists()) {
                file3.createNewFile();
            }

            Map<String, File> fileMap = new HashMap<>();
            fileMap.put("file1", file1);
            fileMap.put("file2", file2);
            fileMap.put("file3", file3);

            // 抢到锁
            for (Map.Entry<String, List<String>> fileContent : contents.entrySet()) {
                synchronized (contents) {
                String file = fileContent.getKey();
                List<String> contentTemp = fileContent.getValue();
                for (int i = 0;i<contentTemp.size();i++) {
                    String content = contentTemp.get(i);

                    if (printContent.equals(content)) {
                        // 对该文件加锁
                        RandomAccessFile out = new RandomAccessFile(fileMap.get(file), "rw");
                        FileChannel fcout = out.getChannel();
                        FileLock flout = null;
                        while (true) {
                            try {
                                flout = fcout.tryLock();
                                break;
                            } catch (Exception e) {
                                sleep(100);
                            }
                        }
                        out.write(content.getBytes());

                        flout.release();
                        fcout.close();
                        out.close();
                        out = null;
                    } else {
                        sleep(100);
                        i--;
                    }
                }
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

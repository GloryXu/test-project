package com.redsun.file;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

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
}

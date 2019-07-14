package com.redsun.dubbo.consumer;

import com.redsun.dubbo.provider.ProviderI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xugr on 2017/3/22.
 */
@Slf4j
@Service
public class Consumer {

    @Autowired
    private ProviderI providerI;

    public void testProvider() {
        log.info("testProvier consumer start call!");
        providerI.queryInfo(22L);
    }
}

package com.redsun.sofa.consumer;

import com.redsun.sofa.provider.ProviderI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuguangrong
 * @description sofa 消费者
 * @date Created at 15:43 2019/2/21
 */
@Slf4j
@Service
public class Consumer {

    @Autowired
    private ProviderI provider;

    public void testProvider() {
        log.info(" ------------consumer start call!--------------------");
        provider.queryInfo(22L);
    }

}

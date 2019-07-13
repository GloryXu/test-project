package com.redsun.sofa.provider.impl;

import com.redsun.sofa.ProviderI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuguangrong
 * @description 服务提供者
 * @date Created at 15:51 2019/2/21
 */
public class Provider implements ProviderI {
    private Logger log = LoggerFactory.getLogger(Provider.class);

    @Override
    public Long queryInfo(Long id) {
        log.info(" -------------------provider invoke success! id = {} ", id);
        return id * 2;
    }

}

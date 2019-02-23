package com.redsun.sofa.provider.impl;

import com.redsun.sofa.provider.ProviderI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xuguangrong
 * @description 服务提供者
 * @date Created at 15:51 2019/2/21
 */
@Slf4j
@Service
public class Provider implements ProviderI {

    @Override
    public void queryInfo(Long id) {
        log.info(" -------------------provider invoke success! id = {} ", id);
    }

}

package com.redsun.dubbo.provider.impl;

import com.redsun.dubbo.provider.ProviderI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by xugr on 2017/3/21.
 */
@Slf4j
@Service
public class ProviderImpl implements ProviderI {
    @Override
    public void queryInfo(Long id) {
        log.info("-----------id = {}", id);
    }
}

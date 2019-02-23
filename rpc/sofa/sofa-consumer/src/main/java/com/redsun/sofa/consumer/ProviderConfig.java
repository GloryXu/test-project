package com.redsun.sofa.consumer;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.redsun.sofa.provider.ProviderI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuguangrong
 * @description 生成bean
 * @date Created at 16:02 2019/2/23
 */
@Configuration
public class ProviderConfig extends ConsumerConfig {

    @Autowired
    private RegistryConfig registryConfig;

    @Bean
    public ProviderI getProvider() {
        ConsumerConfig<ProviderI> consumerConfig = new ConsumerConfig<ProviderI>()
                .setInterfaceId(ProviderI.class.getName())
                .setRegistry(registryConfig)
                .setTimeout(3000);
        return consumerConfig.refer();
    }
}

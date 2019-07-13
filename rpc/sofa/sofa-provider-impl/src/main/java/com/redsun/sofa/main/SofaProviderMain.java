package com.redsun.sofa.main;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.redsun.sofa.ProviderI;
import com.redsun.sofa.provider.impl.Provider;

import java.io.IOException;

/**
 * @author xuguangrong
 * @description sofa provider main
 * @date Created at 17:51 2019/6/5
 */
public class SofaProviderMain {

    public static void main(String[] args) throws IOException {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(22101).setDaemon(false).setProtocol("bolt");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper").setAddress("127.0.0.1:2181");

        Provider provider = new Provider();

        ProviderConfig<ProviderI> providerConfig = new ProviderConfig<>();
        providerConfig.setInterfaceId(ProviderI.class.getName())
                .setRef(provider)
                .setServer(serverConfig)
                .setRegistry(registryConfig)
                .setApplication(new ApplicationConfig().setAppName("glory"))
                .export();

        System.out.println("---------发布完成---------");
    }

}

package com.redsun.dubbo.generic.main;

import com.redsun.dubbo.generic.provider.DemoServiceImpl;
import com.redsun.dubbo.provider.DemoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @author xuguangrong
 * @description dubbo泛化 provider
 * @date Created at 18:19 2019/7/14
 */
public class GenericProviderMain {

    public static void main(String[] args) throws IOException {
        ServiceConfig<DemoServiceImpl> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("dubbo-demo-generic-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());
        service.export();

        System.in.read();
    }

}

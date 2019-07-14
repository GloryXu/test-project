package com.redsun.dubbo.generic.main;

import com.redsun.dubbo.provider.DemoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.IOException;

/**
 * @author xuguangrong
 * @description 泛化消费者执行类
 * @date Created at 21:59 2019/7/14
 */
public class GenericConsumerMain {

    public static void main(String[] args) throws IOException {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-generic-consumer");
        applicationConfig.setQosPort(22221);// 默认22222
        reference.setApplication(applicationConfig);
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface(DemoService.class);

        DemoService service = reference.get();
        String message = service.sayHello("dubbo");
        System.out.println(message);

        System.in.read();
    }

}

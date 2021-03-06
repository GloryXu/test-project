package com.redsun.dubbo.generic.main;

import com.redsun.dubbo.generic.provider.DemoServiceImpl;
import com.redsun.dubbo.provider.DemoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author xuguangrong
 * @description 泛化消费者执行类
 * 依赖接口
 * @date Created at 21:59 2019/7/14
 */
public class GenericConsumerMain {
    private static final Logger logger = LoggerFactory.getLogger(GenericConsumerMain.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-generic-consumer");
        applicationConfig.setQosPort(22221);// 默认22222
        reference.setApplication(applicationConfig);
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface(DemoService.class);
        reference.setGroup("providerGroup");// 需要和服务提供者一致，否则报错
//        boolean isLazy = false;
        boolean isLazy = true;
        reference.setLazy(isLazy);

        ConsumerConfig consumerConfig = new ConsumerConfig();
//        consumerConfig.setLazy(isLazy);
        consumerConfig.setCorethreads(2);
        consumerConfig.setGroup("consumerGroup");
        reference.setConsumer(consumerConfig);

        DemoService service = reference.get();
        Thread.sleep(3000);
        long startTime = System.currentTimeMillis();

        String message = service.sayHello("dubbo");
        logger.info((isLazy ? "延时":"非延时") + "加载响应耗时：" + (System.currentTimeMillis() - startTime) + "ms, 响应信息：" + message);

        System.in.read();
    }

}

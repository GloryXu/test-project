package com.redsun.dubbo.generic.main;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author xuguangrong
 * @description 泛化消费者执行类
 * 不依赖接口
 * @date Created at 21:59 2019/7/14
 */
public class GenericConsumerMain {
    private static final Logger logger = LoggerFactory.getLogger(GenericConsumerMain.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        ApplicationConfig applicationConfig = new ApplicationConfig("dubbo-demo-generic-consumer");
        applicationConfig.setQosPort(22221);// 默认22222
        reference.setApplication(applicationConfig);
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface("com.redsun.dubbo.provider.DemoService");
        reference.setGroup("providerGroup");// 需要和服务提供者一致，否则报错
        reference.setGeneric("true");
//        boolean isLazy = false;// 默认false
        boolean isLazy = true;
        reference.setLazy(isLazy);

        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCorethreads(2);
        consumerConfig.setGroup("consumerGroup");
        reference.setConsumer(consumerConfig);

        GenericService service = reference.get();
        if (!isLazy) {
            // 不延时
            Thread.sleep(1000);
        }

        long startTime = System.currentTimeMillis();
        Object response = service.$invoke("sayHello", new String[]{String.class.getName()}, new Object[]{"dubbo"});
        logger.info((isLazy ? "延时":"非延时") + "加载响应耗时：" + (System.currentTimeMillis() - startTime) + "ms, 响应信息：" + response);

        startTime = System.currentTimeMillis();
        Object response1 = service.$invoke("sayHello", new String[]{String.class.getName()}, new Object[]{"第二次调用"});
        logger.info("第二次调用耗时" + (System.currentTimeMillis() - startTime));
        logger.info("response type is = " + response1.getClass().getName());

        System.in.read();
    }

}

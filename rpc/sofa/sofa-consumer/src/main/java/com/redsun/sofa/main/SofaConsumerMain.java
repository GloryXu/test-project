package com.redsun.sofa.main;

import com.alipay.sofa.rpc.api.GenericService;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;

import java.io.IOException;

/**
 * @author xuguangrong
 * @description main类
 * @date Created at 15:44 2019/2/21
 */
public class SofaConsumerMain {

    private static String INTERFACE_ID = "com.redsun.sofa.ProviderI";
    private static String METHOD_NAME = "queryInfo";
    private static String[] ARG_TYPES = {"Long"};// 参数类型
    private static Object[] ARGS = {22L};// 方法参数

    public static void main(String[] args) throws IOException {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper").setAddress("127.0.0.1:2181");

        ConsumerConfig<GenericService> consumerConfig = new ConsumerConfig<GenericService>()
                .setInterfaceId(INTERFACE_ID)
                .setRegistry(registryConfig)
                .setGeneric(true)// 是否泛化，默认false，需要从classpath从加载该接口类interfaceId
                .setTimeout(3000);

        GenericService genericService = consumerConfig.refer();
        Object result = genericService.$invoke(METHOD_NAME, ARG_TYPES, ARGS);

        System.out.println("--------response--------" + result);
    }

}

package org.apache.dubbo.samples.generic.call;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.dubbo.samples.generic.call.impl.GenericImplOfHelloService;

/**
 * Created by ZDZ on 2019/5/19.
 */
public class GenericImplProvider {

    public static void main(String[] args) throws Exception{
        new EmbeddedZooKeeper(2181, false).start();
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-impl-provider");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");

//        用org.apache.dubbo.rpc.service.GenericService可以代替所有接口实现
        GenericService helloService = new GenericImplOfHelloService();

//        该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        ServiceConfig<GenericService> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
//        弱类型接口名
        service.setInterface("org.apache.dubbo.samples.generic.call.api.HelloService");
//        指向一个通用服务实现
        service.setRef(helloService);
        service.setAsync(true);
//        暴露及注册服务
        service.export();

        System.in.read();
    }
}

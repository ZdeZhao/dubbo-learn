package org.apache.dubbo.samples.generic.all;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ZDZ on 2019/5/20.
 */
public class GenericCallConsumer {

    private static GenericService genericService;

    public static void main(String[] args){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("generic-call-consumer");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface("org.apache.dubbo.samples.generic.call.api.HelloService");
        applicationConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setGeneric(true);   //申明为泛化接口
        referenceConfig.setAsync(true);     // 结果会异步的形式返回
        referenceConfig.setTimeout(10000);
        genericService = referenceConfig.get();
        $invokeWithNormalSignature();
//        泛型暂不支持异步
//        $invokeWithAsyncSignature();
    }

    public static void $invokeWithNormalSignature() {
        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"world"});
        /**
         * RpcContext: ThreadLocal的临时状态记录器，当接受到RPC请求或发起RPC请求时，RpcContext的状态都会变化。
         */
        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((value, t) -> {
            System.err.println(value);  // 设置setAsync(true)以后结果异步返回，在这里接受
        });
        System.err.println("是否为消费端: " + RpcContext.getContext().isConsumerSide());
        System.err.println("提供方的IP: " + RpcContext.getContext().getRemoteHost());
        System.err.println("提供方的地址: " + RpcContext.getContext().getRemoteAddress());
        System.err.println("提供方的端口: " + RpcContext.getContext().getRemotePort());
        System.out.println(".....。" + result);  // 设置结果异步返回以后，在这里result为null
    }

    public static void $invokeWithAsyncSignature() {
        Object result = genericService.$invoke("sayHelloAsync", new String[]{"java.lang.String"}, new Object[]{"world"});

        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((value, t) -> {
            System.err.println(value);
        });

        System.out.println("...." + result);
    }

}

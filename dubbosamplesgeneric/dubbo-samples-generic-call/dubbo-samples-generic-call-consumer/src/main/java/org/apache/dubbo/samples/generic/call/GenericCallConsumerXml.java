package org.apache.dubbo.samples.generic.call;

import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.samples.generic.call.api.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ZDZ on 2019/5/22.
 */
public class GenericCallConsumerXml {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/consumer.xml"});
        context.start();
        HelloService helloService = (HelloService)context.getBean("helloService");
        CompletableFuture<String> future =  helloService.sayHelloAsync("zdz call async");
        future.whenComplete((v, t) -> {
            if (t != null){
                t.printStackTrace();
            } else {
                System.err.println("Response：" + v);
            }
        });
        System.err.println("Executed before response return.");

        System.err.println("RpcContext异步调用");
        HelloService asyncHelloService = (HelloService)context.getBean("asyncHelloService");
//        因为在xml中声明为异步方法，所以返回值为null
        System.err.println("Provider同步Consumer异步：" + asyncHelloService.sayHello("world"));
        CompletableFuture<String> asyncFuture = RpcContext.getContext().getCompletableFuture();
        asyncFuture.whenComplete((v, t) -> {
            if (t == null) {
//                异步方法，返回值在这里获取
                System.err.println(v);
            } else {
                t.printStackTrace();
            }
        });
        System.err.println("测试Provider异步执行Consumer同步执行，利用CompletableFuture签名接口");
        CompletableFuture<String> rpcAsyncFuture = helloService.sayHelloRpcAsync("zdz");
        rpcAsyncFuture.whenComplete((v, t) -> {
            if (t == null) {
                System.err.println("P异步，C异步" + v);
            } else {
                t.printStackTrace();
            }
        });
        System.err.println("P异步C同步：" + rpcAsyncFuture); //失败
//        asyncContext测试
        String asyncContextFuture = helloService.sayHelloAsyncContext("zdz");   //P异步C同步
        System.err.println("asyncContext Provider异步Consumer同步：" + asyncContextFuture);
    }
}

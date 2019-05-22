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
        System.out.println("Executed before response return.");

        System.err.println("RpcContext异步调用");
        HelloService asyncHelloService = (HelloService)context.getBean("asyncHelloService");
//        因为在xml中声明为异步方法，所以返回值为null
        System.out.println("直接调用初始声明为同步的接口：" + asyncHelloService.sayHello("world"));
        CompletableFuture<String> asyncFuture = RpcContext.getContext().getCompletableFuture();
        asyncFuture.whenComplete((v, t) -> {
            if (t == null) {
//                异步方法，返回值在这里获取
                System.err.println(v);
            } else {
                t.printStackTrace();
            }
        });

    }
}

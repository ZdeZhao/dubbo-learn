package org.apache.dubbo.samples.generic.call;

import org.apache.dubbo.samples.generic.call.api.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ZDZ on 2019/5/19.
 */
public class GenericImplConsumer {
    private static HelloService helloService;

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/generic-impl-consumer.xml"});
        context.start();
        helloService = (HelloService) context.getBean("helloService");

        syncCall();
//        使用泛化调用时使用异步失败
        futureCall();

        System.in.read();
    }

    private static void futureCall() {
        CompletableFuture<String> future = helloService.sayHelloAsync("world");
        future.whenComplete((v, t) -> {
            System.err.print(v);
        });

    }

    private static void syncCall() {
        String syncCallResult = helloService.sayHello("world");
        System.err.println(syncCallResult);
    }
}

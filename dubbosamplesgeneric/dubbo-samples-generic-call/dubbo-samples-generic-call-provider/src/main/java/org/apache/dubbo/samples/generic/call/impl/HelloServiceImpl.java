package org.apache.dubbo.samples.generic.call.impl;

import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.samples.generic.call.api.GenericType;
import org.apache.dubbo.samples.generic.call.api.HelloService;
import org.apache.dubbo.samples.generic.call.api.Person;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ZDZ on 2019/5/20.
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        System.err.println("是否为提供方：" + RpcContext.getContext().isProviderSide());
        System.err.println("调用方IP：" + RpcContext.getContext().getRemoteAddress());
        System.err.println("URL参数：" + RpcContext.getContext().getUrls());
        return "======" +"Hello " + name + "======";
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        System.err.println("executing");
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            future.complete("hello, " + name);
        }).start();
        return future;
    }

    @Override
    public CompletableFuture<String> sayHelloAsyncComplex(String name) {
        return null;
    }

    @Override
    public CompletableFuture<GenericType<Person>> sayHelloAsyncGenericComplex(String name) {
        return null;
    }

    @Override
    public CompletableFuture<String> sayHelloRpcAsync(String name) {
        RpcContext context = RpcContext.getContext();
//        锦衣为supplyAsync体用自定义线程池，避免使用本地JDK公共线程池
//        return supplyAysnc 将业务执行从dubbo线程切换到业务线程执行，避免阻塞dubbo线程池
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(context.getAttachment("consumer-key1"));
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "async response from Provider";
        });
    }

    @Override
    public String sayHelloAsyncContext(String name) {
        final AsyncContext asyncContext = RpcContext.startAsync();
        new Thread(() -> {
//            如果要使用上下文，则必须要放在第一句执行
            asyncContext.signalContextSwitch();
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
//            写回应
            asyncContext.write("hello " + name + ", response from provider AsyncContext");
        }).start();
        return null;
    }
}

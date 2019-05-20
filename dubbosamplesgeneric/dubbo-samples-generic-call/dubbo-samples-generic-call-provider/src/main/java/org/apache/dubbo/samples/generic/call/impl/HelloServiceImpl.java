package org.apache.dubbo.samples.generic.call.impl;

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
            future.complete("hahaha");
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
}

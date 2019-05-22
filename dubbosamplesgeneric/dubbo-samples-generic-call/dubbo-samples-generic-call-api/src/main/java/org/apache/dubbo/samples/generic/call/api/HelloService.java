package org.apache.dubbo.samples.generic.call.api;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ZDZ on 2019/5/20.
 */
public interface HelloService {

    String sayHello(String name);

    default CompletableFuture<String> sayAysncHello(String name){
        return CompletableFuture.completedFuture(sayHello(name));
    }

    CompletableFuture<String> sayHelloAsync(String name);

    CompletableFuture<String> sayHelloAsyncComplex(String name);

    CompletableFuture<GenericType<Person>> sayHelloAsyncGenericComplex(String name);
}

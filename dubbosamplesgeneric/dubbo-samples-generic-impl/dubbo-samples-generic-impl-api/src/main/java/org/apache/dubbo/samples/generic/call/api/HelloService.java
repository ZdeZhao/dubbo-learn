package org.apache.dubbo.samples.generic.call.api;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ZDZ on 2019/5/19.
 */
public interface HelloService {
    String sayHello(String name);

    CompletableFuture<String> sayHelloAsync(String name);
}

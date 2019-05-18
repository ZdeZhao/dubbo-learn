package org.apache.dubbo.samples.annotation.api;

/**
 * Created by ZDZ on 2019/5/18.
 */
public interface HelloService {
    String sayHello(String name);

    default String sayGoodbye(String name){
        return "Goodbye, " + name;
    }
}

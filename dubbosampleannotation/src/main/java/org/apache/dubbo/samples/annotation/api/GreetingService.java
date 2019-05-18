package org.apache.dubbo.samples.annotation.api;

/**
 * Created by ZDZ on 2019/5/18.
 */
public interface GreetingService {
    String greeting(String name);

    default String replyGreeting(String name){
        return "Fine, " + name;
    }
}

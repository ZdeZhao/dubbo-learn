package org.apache.dubbo.samples.annotation.impl;

import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.samples.annotation.api.HelloService;

/**
 * Created by ZDZ on 2019/5/18.
 */
@Service(methods = {@Method(name ="sayGoodbye", timeout = 250, retries = 1)})
public class HelloServiceImpl implements HelloService{

    @Override
    public String sayHello(String name) {
        System.out.println("provider received invoke of sayHello: " + name);
        sleepWhile();
        return "Annotation, hello " +name;
    }

    @Override
    public String sayGoodbye(String name) {
        System.out.println("provider received invoke of sayGoodbye: " + name);
        sleepWhile();
        return "Goodbye, " + name;
    }

    private void sleepWhile(){
        try{
            Thread.sleep(300);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

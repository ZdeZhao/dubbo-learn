package org.apache.dubbo.samples.annotation.action;

import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.samples.annotation.AnnotationConstants;
import org.apache.dubbo.samples.annotation.api.GreetingService;
import org.apache.dubbo.samples.annotation.api.HelloService;
import org.springframework.stereotype.Component;

/**
 * Created by ZDZ on 2019/5/18.
 */
@Component("annotationAction")
public class AnnotationAction {

    @Reference(interfaceClass = HelloService.class)
    private HelloService helloService;

    @Reference(interfaceClass = GreetingService.class,
            version = AnnotationConstants.VERSION,
            methods = {@Method(name = "greeting", timeout = 250, retries = 1)})
    private GreetingService greetingService;

    public String doSayHello(String name){
        try{
            return helloService.sayHello(name);
        }catch (Exception e){
            e.printStackTrace();
            return "Throw Exception";
        }
    }
    public String doSayGoodbye(String name){
        try{
            return helloService.sayGoodbye(name);
        }catch (Exception e){
            e.printStackTrace();
            return "Throw Exception";
        }
    }
    public String doGreeting(String name){
        try{
            return greetingService.greeting(name);
        }catch (Exception e){
            e.printStackTrace();
            return "Throw Exception";
        }
    }
    public String replyGreeting(String name){
        try {
            return greetingService.replyGreeting(name);
        }catch (Exception e){
            e.printStackTrace();
            return "Throw Exception";
        }
    }
}

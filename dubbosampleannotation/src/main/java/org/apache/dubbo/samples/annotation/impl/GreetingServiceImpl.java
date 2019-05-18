package org.apache.dubbo.samples.annotation.impl;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.samples.annotation.AnnotationConstants;
import org.apache.dubbo.samples.annotation.api.GreetingService;

/**
 * Created by ZDZ on 2019/5/18.
 */
@Service(version = AnnotationConstants.VERSION)
public class GreetingServiceImpl implements GreetingService{
    @Override
    public String greeting(String name) {
        System.out.println("provider received invoke of greeting: " + name);
        sleepWhile();
        return "Annotation, greeting " + name;
    }

    @Override
    public String replyGreeting(String name) {
        System.out.println("provider received invoke of replyGreeting: " + name);
        sleepWhile();
        return "Annotation, fine " + name;
    }
    private void sleepWhile(){
        try{
            Thread.sleep(300);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

package org.apache.dubbo.samples.annotation;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.samples.annotation.action.AnnotationAction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by ZDZ on 2019/5/18.
 */
public class ConsumerBootstrap {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        final AnnotationAction annotationAction = (AnnotationAction) context.getBean("annotationAction");
        System.out.println("hello: " + annotationAction.doSayHello("world"));
        System.out.println("goodbye: " + annotationAction.doSayGoodbye("world"));
        System.out.println("greeting: " + annotationAction.doGreeting("world"));
        System.out.println("reply: " + annotationAction.replyGreeting("world"));
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "org.apache.dubbo.samples.annotation.action")
    @PropertySource("classpath:/spring/dubbo-consumer.properties")
    @ComponentScan(value = {"org.apache.dubbo.samples.annotation.action"})
    static public class ConsumerConfiguration{}
}

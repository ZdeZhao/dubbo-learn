package org.apache.dubbo.samples.callback;

import org.apache.dubbo.samples.callback.EmbeddedZooKeeper;
import org.apache.dubbo.samples.callback.api.CallbackService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZDZ on 2019/5/26.
 */
public class CallbackConsumer {

    public static void main(String[] args) throws  Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/callback-consumer.xml"});
        context.start();
        CallbackService callbackService = (CallbackService) context.getBean("callbackService");
        callbackService.addListener("foo.bar", msg -> System.err.println("callback1: " + msg));
    }
}

package org.apache.dubbo.samples.callback;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZDZ on 2019/5/26.
 */
public class CallbackProvider {

    public static void main(String[] args) throws Exception {
        new EmbeddedZooKeeper(2181, false).start();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/callback-provider.xml"});
        context.start();
        System.in.read();
    }
}

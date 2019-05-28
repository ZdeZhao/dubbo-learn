package org.apache.dubbo.samples.notify;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zdz on 2019/5/28.
 */
public class NotifyProvider {
    public static void main(String[] args) throws Exception {
        new EmbeddedZooKeeper(2181, false).start();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/notify-provider.xml"});
        context.start();
        System.in.read();
    }
}

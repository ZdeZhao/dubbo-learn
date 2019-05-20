package org.apache.dubbo.samples.generic.call;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZDZ on 2019/5/20.
 */
public class GenericCallProvider {

    public static void main(String[] args) throws Exception {
        new EmbeddedZooKeeper(2181, false).start();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/generic-provider.xml"});
        context.start();
        System.in.read();
    }
}

package org.apache.dubbo.samples.mock;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zdz on 2019/5/31.
 */
public class MockProvider {

    public static void main(String[] args) throws Exception {
        new EmbeddedZooKeeper(2181, false).start();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/mock-provider.xml"}
        );
        context.start();
        System.in.read();
    }
}

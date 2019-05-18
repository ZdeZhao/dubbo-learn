package org.apache.dubbo.samples.validation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZDZ on 2019/5/18.
 */
public class ValidationProvider {

    public static void main(String[] args) throws Exception{
        new EmbeddedZooKeeper(2181, false).start();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/validation-provider.xml"});
        context.start();
        System.in.read();
    }
}

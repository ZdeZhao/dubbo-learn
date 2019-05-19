package org.apache.dubbo.samples.cache;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZDZ on 2019/5/19.
 */
public class CacheProvider {

    public static void main(String[] args) throws Exception{
        new EmbeddedZooKeeper(2181, false).start();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/cache-provider.xml"});
        context.start();
        System.in.read();
    }
}

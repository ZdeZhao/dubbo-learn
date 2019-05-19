package org.apache.dubbo.samples.cache;

import org.apache.dubbo.samples.cache.action.GetCache;
import org.apache.dubbo.samples.cache.api.CacheService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZDZ on 2019/5/19.
 * 开启缓存后，若为相同的invoke请求，且缓存中有相应的应答，则不会去请求provider，而是直接返回结果
 */
public class CacheConsumer {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/cache-consumer.xml"});
        context.start();
        CacheService cacheService = (CacheService) context.getBean("cacheService");
        GetCache.getCache("Main", cacheService, 1001);
    }
}

package org.apache.dubbo.samples.cache;

import org.apache.dubbo.samples.cache.action.GetCache;
import org.apache.dubbo.samples.cache.api.CacheService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZDZ on 2019/5/19.
 * 采用threadLocal缓存机制，执行一万次请求，仍不能刷新缓存，测试失败
 */
public class CacheConsumerThreadLocal {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/cache-consumer-thread-local.xml"});
        context.start();
        CacheService cacheService = (CacheService) context.getBean("cacheService");
        GetCache.getCache("Main", cacheService, 10000);
        Thread thread = new Thread(new MyRunnable("thread", cacheService, 10000));
        thread.start();
    }
    static class MyRunnable implements Runnable{
        String threadName;
        CacheService cacheService;
        int time;
        public MyRunnable(String threadName, CacheService cacheService, int time){
            this.cacheService = cacheService;
            this.threadName = threadName;
            this.time = time;
        }
        @Override
        public void run() {
            try{
                GetCache.getCache(this.threadName, this.cacheService, this.time);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}

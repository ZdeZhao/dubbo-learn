package org.apache.dubbo.samples.cache.action;

import org.apache.dubbo.samples.cache.api.CacheService;

/**
 * Created by ZDZ on 2019/5/19.
 */
public class GetCache {
    public static void getCache(String threadName, CacheService cacheService, int time) throws Exception {
        String fix = null;
        for (int i=0; i<5; i++){
            String result = cacheService.findCache("0");
            if (fix == null || fix.equals(result)){
                System.out.println(threadName + " OK: " + result);
            } else {
                System.out.println(threadName + " ERROR: " + result);
            }
            fix = result;
            Thread.sleep(500);
        }

//        default cache.size is 1000 for LRU, should have cache expired if invoke more than 1001 times
//        默认LRU缓存大小为1000，至少执行1001次
        for (int n = 0; n < time; n++) {
            String pre = null;
            for (int i = 0; i < 10; i++) {
                String result = cacheService.findCache(String.valueOf(n));
                if (pre != null && !pre.equals(result)){
                    System.out.println(threadName + " Error: " + result);
                }
                pre = result;
            }
        }

//        验证是否第一次的cache过期了
        String result = cacheService.findCache("0");
        if (fix != null && !fix.equals(result)){
            System.out.println(threadName + "fix: " + fix);
            System.out.println(threadName + " OK: " + result);
        } else {
            System.out.println(threadName + " ERROR: " + result);
        }
        System.out.println(cacheService.findCache("1"));
    }
}

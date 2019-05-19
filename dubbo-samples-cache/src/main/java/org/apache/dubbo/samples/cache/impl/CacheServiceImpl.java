package org.apache.dubbo.samples.cache.impl;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.samples.cache.api.CacheService;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZDZ on 2019/5/19.
 */
@Service
public class CacheServiceImpl implements CacheService {

    private final AtomicInteger i = new AtomicInteger();
    @Override
    public String findCache(String id) {
        return "reqeuest: " + id + ", response: " + i.getAndIncrement();
    }
}

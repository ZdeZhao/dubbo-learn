package org.apache.dubbo.samples.annotation.api.impl;

import org.apache.dubbo.samples.annotation.api.api.HelloService;

/**
 * Created by ZDZ on 2019/5/18.
 */
@Service()
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return null;
    }
}

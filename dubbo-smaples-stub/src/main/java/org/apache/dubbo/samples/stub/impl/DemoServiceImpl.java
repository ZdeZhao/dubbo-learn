package org.apache.dubbo.samples.stub.impl;

import org.apache.dubbo.samples.stub.api.DemoService;

/**
 * Created by zdz on 2019/5/31.
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "greeting " + name;
    }
}

package org.apache.dubbo.samples.mock.impl;

import org.apache.dubbo.samples.mock.api.DemoService;

/**
 * Created by zdz on 2019/5/31.
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "hello " + name;
    }
}

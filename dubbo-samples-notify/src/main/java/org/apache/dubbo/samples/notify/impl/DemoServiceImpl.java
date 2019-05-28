package org.apache.dubbo.samples.notify.impl;

import org.apache.dubbo.samples.notify.api.DemoService;

/**
 * Created by zdz on 2019/5/28.
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(int id) {
        return "aaa" + id;
    }
}

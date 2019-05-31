package org.apache.dubbo.samples.stub.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zdz on 2019/5/31.
 */
public class DemoServiceStub implements DemoService {

    private static Logger logger = LoggerFactory.getLogger(DemoServiceStub.class);

    private final DemoService demoService;

    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) {
        logger.info("Before execute remote service, parameter: " + name);
        try {
            String result = demoService.sayHello(name);
            logger.info("after execute remote service, result: " + result);
            return result;
        } catch (Exception e) {
            logger.warn("fail to execute service", e);
            return null;
        }
    }
}

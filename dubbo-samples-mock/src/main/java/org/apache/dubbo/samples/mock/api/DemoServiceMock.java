package org.apache.dubbo.samples.mock.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zdz on 2019/5/31.
 */
public class DemoServiceMock implements DemoService {

    private static Logger logger = LoggerFactory.getLogger(DemoServiceMock.class);

//    RpcException发生时执行mock
    @Override
    public String sayHello(String name) {
        logger.warn("about to execute mock: " + DemoServiceMock.class.getSimpleName());
        return "Mock " + name;
    }
}

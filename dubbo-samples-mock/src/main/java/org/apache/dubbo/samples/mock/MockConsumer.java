package org.apache.dubbo.samples.mock;

import org.apache.dubbo.samples.mock.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zdz on 2019/5/31.
 */
public class MockConsumer {
    private static Logger logger = LoggerFactory.getLogger(MockConsumer.class);

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/mock-consumer.xml"}
        );
        context.start();
        DemoService demoService = context.getBean("demoService", DemoService.class);
        String hello = demoService.sayHello("zdz");
        logger.info("result: " + hello);
    }
}

package org.apache.dubbo.samples.stub;

import org.apache.dubbo.samples.stub.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zdz on 2019/5/31.
 */
public class StubConsumer {

    private static Logger logger = LoggerFactory.getLogger(StubConsumer.class);

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/stub-consumer.xml"}
        );
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");
        logger.info("result: " + demoService.sayHello("zdz"));
    }
}

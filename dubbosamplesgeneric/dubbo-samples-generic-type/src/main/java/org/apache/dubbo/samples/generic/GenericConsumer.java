package org.apache.dubbo.samples.generic;

import org.apache.dubbo.samples.generic.api.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ZDZ on 2019/5/20.
 */
public class GenericConsumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/generic-consumer.xml"});
        context.start();
        IUserService userService = (IUserService) context.getBean("userService");
        IUserService.User user = userService.get(new IUserService.Params("a=b"));
        System.out.println(user);
        System.in.read();
    }
}

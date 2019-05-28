package org.apache.dubbo.samples.notify;

import org.apache.dubbo.samples.notify.api.DemoService;
import org.apache.dubbo.samples.notify.impl.NotifyImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zdz on 2019/5/28.
 */
public class NotifyConsumer {

    public static void main(String[] args) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/notify-consumer.xml"}
        );
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService");
        NotifyImpl notify = (NotifyImpl) context.getBean("demoCallback");
        int id = 1;
        String result = demoService.sayHello(id);
        for (int i = 0; i < 10; i++) {
            if (!notify.ret.containsKey(id)) {
                Thread.sleep(200);
            } else {
                break;
            }
        }
        System.out.println(notify.ret.get(id));
    }
}

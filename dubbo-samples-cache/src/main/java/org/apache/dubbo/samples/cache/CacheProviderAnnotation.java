package org.apache.dubbo.samples.cache;

import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by ZDZ on 2019/5/19.
 */
public class CacheProviderAnnotation {

    public static void main(String[] args) throws Exception {
        new EmbeddedZooKeeper(2181, false).start();
        Thread.sleep(1000);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.in.read();
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "org.apache.dubbo.samples.cache.impl")
    @PropertySource("classpath:/spring/dubbo-provider.properties")
    static public class ProviderConfiguration{
        @Bean
        public ProviderConfig providerConfig() {
            ProviderConfig providerConfig = new ProviderConfig();
            providerConfig.setTimeout(1000);
            return providerConfig;
        }
    }

}

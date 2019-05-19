package org.apache.dubbo.samples.validation;

import org.apache.dubbo.samples.validation.api.ValidationParameter;
import org.apache.dubbo.samples.validation.api.ValidationService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Set;

/**
 * Created by ZDZ on 2019/5/18.
 */
public class ValidationConsumer {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/validation-consumer.xml"});
        context.start();
        ValidationService validationService = (ValidationService)context.getBean("validationService");
        ValidationParameter parameter = new ValidationParameter();
        parameter.setName("liangfei");
        parameter.setEmail("liangfeei@liang.fei");
        parameter.setAge(50);
        parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
        parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
        validationService.save(parameter);
        System.out.println("Validation Save OK");

//        update方法，由于email的NotNull注解只对save生效，所以update时如果email为空，也可以保存成功
        parameter = new ValidationParameter();
        parameter.setName("zdz");
        parameter.setAge(26);
        parameter.setLoginDate(new Date(System.currentTimeMillis() - 1000000));
        parameter.setExpiryDate(new Date(System.currentTimeMillis() + 1000000));
        validationService.update(parameter);
        System.out.println("Validation Update OK");
        // save Error
        try {
            parameter = new ValidationParameter();
            validationService.save(parameter);
            System.out.println("Validation Save ERROR");
        } catch (Exception e){
            ConstraintViolationException ve = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
            System.out.println(violations);
        }

        // delete OK
        validationService.delete(2, "abc");
        System.out.println("Validation Delete OK");

        // Delete Error
        try {
            validationService.delete(0, "abc");
            System.out.println("Validation Delete ERROR");
        } catch (Exception e){
            ConstraintViolationException ve = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = ve.getConstraintViolations();
            System.out.println(violations);
        }
    }
}

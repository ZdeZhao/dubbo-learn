package org.apache.dubbo.samples.validation;

import org.apache.dubbo.samples.validation.api.ValidationParameter;
import org.apache.dubbo.samples.validation.impl.ValidationServiceImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by ZDZ on 2019/5/19.
 */
public class ValidationLocalTest {
    public static void main(String[] args) {
        ValidationParameter parameter = new ValidationParameter();
        parameter.setAge(1);

//        普通方法，解析错误
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ValidationParameter>> constraintViolations = validator.validate(parameter);
        for (ConstraintViolation<ValidationParameter> item : constraintViolations){
            System.out.println(item.getMessage());
        }

//        直接调用save解析不了错误
        ValidationServiceImpl validationService = new ValidationServiceImpl();
        try {
            validationService.save(parameter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package org.apache.dubbo.samples.generic.call.impl;

import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.concurrent.CompletableFuture;

/**
 * Created by ZDZ on 2019/5/19.
 */
public class GenericImplOfHelloService implements GenericService {


    @Override
    public Object $invoke(String method, String[] strings, Object[] objects) throws GenericException {
        if (method.equals("sayHello")){
            System.out.print("executing sayHello");
            throw new RuntimeException("Test biz exception");
        }else if (method.equals("sayHelloAsync")) {
            System.out.print("executing sayHelloAsync");
            return CompletableFuture.completedFuture("future method invoke.");
        }
        GenericException genericException = new GenericException();
        genericException.setExceptionMessage("method does not exist");
        throw genericException;
    }
}

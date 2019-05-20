package org.apache.dubbo.samples.generic.impl;

import org.apache.dubbo.samples.generic.api.IUserService;

/**
 * Created by ZDZ on 2019/5/20.
 */
public class UserServiceImpl implements IUserService{
    @Override
    public User get(Params params) {
        return new User(1, "charles");
    }
}

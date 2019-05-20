package org.apache.dubbo.samples.generic.call.api;

import java.io.Serializable;

/**
 * Created by ZDZ on 2019/5/20.
 */
public class GenericType<T> implements Serializable {
    private T aa;

    public GenericType(T aa) {
        this.aa = aa;
    }

    public T getAa() {
        return aa;
    }

    public void setAa(T aa) {
        this.aa = aa;
    }
}

package org.apache.dubbo.samples.generic.api;

/**
 * Created by ZDZ on 2019/5/20.
 */
public interface IService<P, V> {
    V get(P params);

}

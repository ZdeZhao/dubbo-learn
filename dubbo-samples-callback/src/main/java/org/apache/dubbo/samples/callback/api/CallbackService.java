package org.apache.dubbo.samples.callback.api;

/**
 * Created by ZDZ on 2019/5/26.
 */
public interface CallbackService {

    void addListener(String key, CallbackListener listener);
}

package org.apache.dubbo.samples.notify.api;

/**
 * Created by zdz on 2019/5/28.
 */
public interface Notify {

    void onreturn(String name, int id);

    void onthrow(Throwable ex, String name, int id);

}

package org.apache.dubbo.samples.notify.impl;

import org.apache.dubbo.samples.notify.api.Notify;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zdz on 2019/5/28.
 */
public class NotifyImpl implements Notify {

    public Map<Integer, String> ret = new HashMap<>();

    @Override
    public void onreturn(String name, int id) {
        ret.put(id, name);
        System.out.println("onreturn: " + name);
    }

    @Override
    public void onthrow(Throwable ex, String name, int id) {
        System.out.println("onthrow: " + name);
    }
}

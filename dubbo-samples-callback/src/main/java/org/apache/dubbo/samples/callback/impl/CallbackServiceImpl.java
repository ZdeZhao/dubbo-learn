package org.apache.dubbo.samples.callback.impl;

import org.apache.dubbo.samples.callback.api.CallbackListener;
import org.apache.dubbo.samples.callback.api.CallbackService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZDZ on 2019/5/26.
 */
public class CallbackServiceImpl implements CallbackService {

    private final Map<String, CallbackListener> listeners = new ConcurrentHashMap<>();

    public CallbackServiceImpl() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    for (Map.Entry<String, CallbackListener> entry: listeners.entrySet()) {
                        try {
                            entry.getValue().changed(getChanged(entry.getKey()));
                            System.out.println(entry.getKey());
                        }catch (Throwable t1) {
                            listeners.remove(entry);
                        }
                    }
                    Thread.sleep(5000);
                }catch (Throwable e){
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);  //设置为守护线程（服务线程），默认为用户线程
        /**
         * 用户线程与守护线程的区别
         * 1. 主线程结束后用户线程还会继续执行，JVM存回；而守护线程与JVM的状态由2判断
         * 2. 如果没有用户线程，都是守护线程，那么JVM结束（所有的守护线程也消失）
         */
        t.start();
    }
    @Override
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key, listener);
        listener.changed(getChanged(key));
    }

    private String getChanged(String key) {
        return "Changed[" + key + "]: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}

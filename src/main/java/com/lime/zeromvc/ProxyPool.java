package com.lime.zeromvc;

import java.util.HashMap;
import java.util.Map;

public class ProxyPool {

    /**
     *
     */
    public Map<Class<? extends Proxy>, Proxy> pool;

    /**
     *
     */
    public ProxyPool() {
        pool = new HashMap<Class<? extends Proxy>, Proxy>();
    }

    /**
     * 释放 数据代理对象
     * @param proxy
     * @return
     */
    public boolean dispose(Proxy proxy) {
        if (pool.containsKey(proxy)) {
            pool.remove(proxy);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取数据代理
     *
     * @param proxyClass 所要获取数据代理类的反射对象(如 Object.class)
     * @param <TProxy>   数据代理类的类型
     * @return 获取到的数据代理类
     */
    public <TProxy extends Proxy> TProxy getProxy(Class<TProxy> proxyClass) {
        TProxy proxy = null;
        if (pool.containsKey(proxyClass)) {
            proxy = (TProxy) pool.get(proxyClass);
        } else {
            try {
                proxy = proxyClass.newInstance();
                pool.put(proxyClass, proxy);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return proxy;
    }
}


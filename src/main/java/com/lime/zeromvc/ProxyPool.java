package com.lime.zeromvc;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据代理管理类
 * 每一个管理类中每一个代理类只有一个实例
 * 使用管理类访问代理类时！代理类的实例就像创建
 */
public class ProxyPool {

    /**
     * 存放数据代理容器
     */
    public Map<Class<? extends Proxy>, Proxy> pool;

    /**
     * 数据代理管理类
     */
    public ProxyPool() {
        pool = new HashMap<Class<? extends Proxy>, Proxy>();
    }

    /**
     * 释放 数据代理对象
     *
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


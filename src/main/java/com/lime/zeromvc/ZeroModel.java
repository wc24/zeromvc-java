package com.lime.zeromvc;

import java.util.HashMap;
import java.util.Map;

public class ZeroModel{


    public Map<Class<Proxy>, Proxy> proxyPool;

    public ZeroModel() {
        proxyPool = new HashMap<Class<Proxy>, Proxy>();
    }
    public boolean releaseProxy(Proxy proxy){
        if(proxyPool.containsKey(proxy)){
            proxyPool.remove(proxy);
            return true;
        }else {
            return false;
        }
    }
    public <TProxy extends Proxy> TProxy getProxy(Class<TProxy> proxyClass) {
        TProxy proxy = null;
        if (proxyPool.containsKey(proxyClass)) {
            proxy = (TProxy) proxyPool.get(proxyClass);
        } else {
            try {
                proxy = proxyClass.newInstance();
                proxyPool.put((Class<Proxy>) proxyClass, proxy);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return proxy;
    }
}


package com.lime.zeromvc;

public class ZeroNeure extends Neure {

    protected Zero _zero;

    public <TProxy extends Proxy> TProxy getProxy(Class<TProxy> proxyClass) {
        return _zero.model.getProxy(proxyClass);
    }


}

package com.lime.zeromvc;

import java.util.ArrayList;
import java.util.List;


public abstract class Mediator<TCommandKey, TMediatorKey> extends Neure<Zero<TCommandKey, TMediatorKey>, TMediatorKey, Boolean> {


    private Object group;
    private TMediatorKey type;
    private Zero<TCommandKey, TMediatorKey> zero;

    private List<Proxy> pool;

    public Mediator() {
        pool = new ArrayList<Proxy>();
    }

    public <TProxy extends Proxy> TProxy getProxy(Class<TProxy> proxyClass) {
        return zero.model.getProxy(proxyClass);
    }

    @Override
    public void execute(Boolean isActive) {
        if (isActive) {
            activate();
            if (group != null) {
                if (zero.mediatorKeyGroup.containsKey(group)) {
                    if (zero.mediatorKeyGroup.get(group) != type) {
                        zero.inactivate(zero.mediatorKeyGroup.get(group));
                        zero.mediatorKeyGroup.put(group, type);
                    }
                } else {
                    zero.mediatorKeyGroup.put(group, type);
                }
            }
        } else {
            for (Proxy proxy : pool) {
                proxy.unbind(this);
            }
            if (group != null && zero.mediatorKeyGroup.containsKey(group) && zero.mediatorKeyGroup.get(group) != type) {
                zero.mediatorKeyGroup.remove(type);
            }
            inactivate();
        }

    }

    public void init(Zero<TCommandKey, TMediatorKey> zero, TMediatorKey type) {
        this.zero = zero;
        this.type = type;
    }

    public void command(TCommandKey key, Object date) {
        zero.command(key, date);
    }

    public void command(TCommandKey key) {
        zero.command(key);
    }

    public void addProxy(Proxy proxy) {
        proxy.bind(this);
        pool.add(proxy);
    }

    protected void dispose() {
        zero.view.release(type);
        zero.inactivate(type);
    }

    protected abstract void activate();

    protected abstract void update(Proxy proxy);

    protected abstract void inactivate();
}

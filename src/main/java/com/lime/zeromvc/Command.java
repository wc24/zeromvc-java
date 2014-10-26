package com.lime.zeromvc;

/**
 * Created by linming on 14-10-22.
 */
public abstract class Command<TCommandKey, TMediatorKey, TContent> implements IExecute<Zero<TCommandKey, TMediatorKey>, TCommandKey, TContent> {

    private TCommandKey type;
    private Zero<TCommandKey, TMediatorKey> zero;


    protected void activate(TMediatorKey key) {
        zero.activate(key);
    }


    protected void inactivate(TMediatorKey key) {
        zero.inactivate(key);
    }

    protected void dispose() {
        zero.control.dispose(type);
    }

    /**
     *
     * @param zero
     * @param type
     */
    public void init(Zero<TCommandKey, TMediatorKey> zero, TCommandKey type) {
        this.zero = zero;
        this.type = type;
    }

    /**
     *
     * @param key
     * @param date
     */
    public void command(TCommandKey key, Object date) {
        zero.command(key, date);
    }

    /**
     *
     * @param key
     */
    public void command(TCommandKey key) {
        zero.command(key);
    }

    /**
     *
     * @param proxyClass
     * @param <TProxy>
     * @return
     */
    public <TProxy extends Proxy> TProxy getProxy(Class<TProxy> proxyClass) {
        return zero.model.getProxy(proxyClass);
    }

}

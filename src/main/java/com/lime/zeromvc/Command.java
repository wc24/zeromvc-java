package com.lime.zeromvc;

/**
 * 指令类
 *
 * @param <TCommandKey>  命令枚举类型
 * @param <TMediatorKey> 中介枚举类型
 */
public abstract class Command<TCommandKey, TMediatorKey> {

    protected TCommandKey type;
    protected Zero<TCommandKey, TMediatorKey> zero;

    protected abstract void init();

    protected void activate(TMediatorKey key) {
        zero.activate(key);
    }

    protected void inactivate(TMediatorKey key) {
        zero.inactivate(key);
    }

    /**
     *
     */
    public void dispose() {
        zero.control.dispose(type);
    }

    /**
     * @param zero
     * @param type
     */
    public void init(Object zero, Object type) {
        this.zero = (Zero<TCommandKey, TMediatorKey>) zero;
        this.type = (TCommandKey) type;
        init();
    }

    /**
     * @param key
     * @param args
     */
    public void command(TCommandKey key, Object... args) {
        zero.command(key, args);
    }

    public <TProxy extends Proxy> TProxy getProxy(Class<TProxy> proxyClass) {
        return zero.model.getProxy(proxyClass);
    }
}

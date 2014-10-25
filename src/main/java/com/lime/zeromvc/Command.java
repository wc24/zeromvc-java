package com.lime.zeromvc;

/**
 * Created by linming on 14-10-22.
 */
public abstract class Command<TCommandKey, TMediatorKey, TContent> extends ZeroNeure implements Neure<Zero<TCommandKey, TMediatorKey>, TCommandKey, TContent> {

    private TCommandKey type;
    private Zero<TCommandKey, TMediatorKey> zero;


    protected void activate(TMediatorKey key) {
        zero.activate(key);
    }


    protected void inactivate(TMediatorKey key) {
        zero.inactivate(key);
    }

    protected void dispose() {
        System.out.println(">>>>>>>>>>>>");
        System.out.println(type);
        zero.control.release(type);
    }

    public void init(Zero<TCommandKey, TMediatorKey> zero, TCommandKey type) {
        this.zero = zero;
        _zero= zero;
        this.type = type;
    }

    public void command(TCommandKey key, Object date) {
        zero.command(key, date);
    }

    public void command(TCommandKey key) {
        zero.command(key);
    }
}

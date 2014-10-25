package com.lime.zeromvc;


import java.util.Map;


public class Zero<TCommandKey, TMediatorKey> implements IZero<TCommandKey, TMediatorKey> {
    public Spine<TCommandKey> control;
    public ZeroModel model;
    public Spine<TMediatorKey> view;

    public Map<Object, TMediatorKey> mediatorKeyGroup;

    public Zero() {
        control = new Spine<TCommandKey>(this);
        view = new Spine<TMediatorKey>(this);
        model = new ZeroModel();
    }

    @Override
    public void addCommand(TCommandKey key, Class<?> commandClass) {
        control.addListener(key, commandClass);
    }

    @Override
    public void removeCommand(TCommandKey key, Class<?> commandClass) {
        control.addListener(key, commandClass);

    }

    @Override
    public void addMediator(TMediatorKey key, Class<?> mediatorClass) {
        view.addListener(key, mediatorClass);

    }

    @Override
    public void removeMediator(TMediatorKey key, Class<Mediator> mediatorClass) {
        view.addListener(key, mediatorClass.asSubclass(Neure.class));

    }

    @Override
    public void inactivate(TMediatorKey key) {
        view.notify(key, false);
    }

    @Override
    public void activate(TMediatorKey key) {
        view.notify(key, true);

    }

    @Override
    public void command(TCommandKey key, Object date) {
        control.notify(key, date);

    }

    @Override
    public void command(TCommandKey key) {
        control.notify(key);

    }
}

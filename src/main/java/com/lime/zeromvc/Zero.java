package com.lime.zeromvc;


import java.util.Map;


public class Zero<TCommandKey, TMediatorKey> {
    public Spine<TCommandKey> control;
    public ZeroModel model;
    public Spine<TMediatorKey> view;

    public Map<Object, TMediatorKey> mediatorKeyGroup;

    public Zero() {
        control = new Spine<TCommandKey>(this);
        view = new Spine<TMediatorKey>(this);
        model = new ZeroModel();
    }

    public void addCommand(TCommandKey key, Class<? extends Command> commandClass) {
        control.addListener(key, (Class<Neure>) commandClass.asSubclass(Neure.class));
    }

    public void removeCommand(TCommandKey key, Class<Command> commandClass) {
        control.removeListener(key, (Class<Neure>) commandClass.asSubclass(Neure.class));

    }

    public void addMediator(TMediatorKey key, Class<? extends Neure> mediatorClass) {
        view.addListener(key, (Class<Neure>) mediatorClass.asSubclass(Neure.class));

    }

    public void removeMediator(TMediatorKey key, Class<Mediator> mediatorClass) {
        view.removeListener(key, (Class<Neure>) mediatorClass.asSubclass(Neure.class));

    }

    public void inactivate(TMediatorKey key) {
        view.notify(key, false);
    }

    public void activate(TMediatorKey key) {
        view.notify(key, true);

    }

    public void command(TCommandKey key, Object date) {
        control.notify(key, date);

    }

    public void command(TCommandKey key) {
        control.notify(key);

    }
}

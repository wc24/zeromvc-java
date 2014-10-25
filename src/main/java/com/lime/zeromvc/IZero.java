package com.lime.zeromvc;

/**
 * Created by lime on 14-10-25.
 */
public interface IZero<TCommandKey, TMediatorKey> {
    public void addCommand(TCommandKey key, Class<Neure> commandClass);

    public void removeCommand(TCommandKey key, Class<Neure> commandClass);

    public void addMediator(TMediatorKey key, Class<Neure> mediatorClass);

    public void removeMediator(TMediatorKey key, Class<Neure> mediatorClass);

    public void inactivate(TMediatorKey key);

    public void activate(TMediatorKey key);

    public void command(TCommandKey key,Object date);

    public void command(TCommandKey key);

}

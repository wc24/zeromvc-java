package com.lime.zeromvc;

/**
 * Created by linming on 14-10-22.
 */
public class Observer implements IObserver {

    @Override
    public boolean addListener(String type, ICommand command) {
        return false;
    }

    @Override
    public boolean removeListener(String type, ICommand command) {
        return false;
    }

    @Override
    public boolean clearListener(String type) {
        return false;
    }

    @Override
    public boolean hasListener(String type) {
        return false;
    }

    @Override
    public boolean hasListener(String type, ICommand command) {
        return false;
    }

    @Override
    public boolean notify(IReport report) {
        return false;
    }
}

package com.lime.zeromvc;

/**
 * Created by linming on 14-10-22.
 */
public interface IObserver {
    public boolean addListener(String type,ICommand command);
    public boolean removeListener(String type,ICommand command);
    public boolean clearListener(String type);
    public boolean hasListener(String type);
    public boolean hasListener(String type,ICommand command);
    public boolean notify(IReport report);

}

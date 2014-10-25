package com.lime.zeromvc;

public abstract class Neure<TTarget, TKey, TContent> {
    public abstract void init(TTarget tTarget, TKey type);
    public abstract void execute(TContent tContent);
}

package com.lime.zeromvc;

/**
 * Created by linming on 14-10-25.
 */
public abstract class Neurefffff<TTarget, TKey, TContent> {
    public abstract void init(TTarget tTarget, TKey type);
    public abstract void execute(TContent tContent);
}

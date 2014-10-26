package com.lime.zeromvc;

public interface IExecute<TTarget, TKey, TContent> {

    /**
     *
     * @param target
     * @param type
     */
    public void init(TTarget target, TKey type);

    /**
     *
     * @param tContent
     */
    public void execute(TContent tContent);
}

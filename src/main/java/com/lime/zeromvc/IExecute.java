package com.lime.zeromvc;

public interface IExecute<TTarget, TKey, TContent> {

    /**
     * 初始化
     * 当观察者派发通知时 接口实现类在第一次执行或释放后第一次执行时会进行初始化！
     *
     * @param target 派发者
     * @param type   注册识标
     */
    public void init(TTarget target, TKey type);

    /**
     * 执行
     * 当观察者派发通知时，接口实现类所注册的对应识标时执行本方法！
     *
     * @param tContent
     */
    public void execute(TContent tContent);

    /**
     * 释放
     * 移除观察对接口实现类的实例的引用！
     * 释放不会立即生效！
     */
    public void dispose();
}

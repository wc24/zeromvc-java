package com.lime.zeromvc;

import java.util.Map;

/**
 * 零框架 主类
 *
 * @param <TCommandKey>  命令枚举类型
 * @param <TMediatorKey> 中介枚举类型
 * @author lime
 */

public class Zero<TCommandKey, TMediatorKey> {

    /**
     * 控制器
     * (c)
     * 将相关的命令类反射对应命令枚举 形成控制器
     */
    public Observer<TCommandKey> control;

    /**
     * 数据模形
     * (m)
     * 将相关的数据代理进行单例化管理 形成数据模形
     */
    public ProxyPool model;

    /**
     * 视图管理器
     * (V)
     * 将相关的视图中介类反射对应中介枚举 形成视图管理器
     */
    public Observer<TMediatorKey> view;

    /**
     * 将中介进行分组，同组的中介只会有一个在激活状态
     */
    public Map<Object, TMediatorKey> mediatorKeyGroup;

    /**
     *
     */
    public Zero() {
        control = new Observer<TCommandKey>(this);
        view = new Observer<TMediatorKey>(this);
        model = new ProxyPool();
    }

    /**
     * 添加命令
     *
     * @param key          命令的枚举值
     * @param commandClass 所要添加的命令类的反射对象(如 Object.class)
     */
    public void addCommand(TCommandKey key, Class<? extends Command> commandClass) {
        control.addListener(key, commandClass);
    }

    /**
     * 移除命令
     *
     * @param key          命令的枚举值
     * @param commandClass 所要添加的命令类的反射对象(如 Object.class)
     */
    public void removeCommand(TCommandKey key, Class<? extends Command> commandClass) {
        control.removeListener(key, commandClass);

    }

    /**
     * 添加中介
     *
     * @param key           中介的枚举值
     * @param mediatorClass 所要添加的中介类的反射对象(如 Object.class)
     */
    public void addMediator(TMediatorKey key, Class<? extends Mediator> mediatorClass) {
        view.addListener(key, mediatorClass);

    }

    /**
     * 移除中介
     *
     * @param key           中介的枚举值
     * @param mediatorClass 所要添加的中介类的反射对象(如 Object.class)
     */
    public void removeMediator(TMediatorKey key, Class<? extends Mediator> mediatorClass) {
        view.removeListener(key, mediatorClass);

    }

    /**
     * 灭活中介
     *
     * @param key 中介的枚举值
     */
    public void inactivate(TMediatorKey key) {
        view.notify(key, false);
    }

    /**
     * 激活中介
     *
     * @param key 中介的枚举值
     */
    public void activate(TMediatorKey key) {
        view.notify(key, true);

    }

    /**
     * 派发命令
     *
     * @param key  命令的枚举值
     * @param date 派发命令的参数数据
     */
    public void command(TCommandKey key, Object date) {
        control.notify(key, date);

    }

    /**
     * 派发命令
     *
     * @param key 命令的枚举值
     */
    public void command(TCommandKey key) {
        control.notify(key);

    }
}

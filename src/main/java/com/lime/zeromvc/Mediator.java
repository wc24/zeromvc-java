package com.lime.zeromvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介类！
 * 中介类是视图层表现层用于主动通知视图表现或状态通信。
 * 中介类只有在激活状下才会主动通知！
 * 中介类可以进行分组，同组的中介只会有一个在激活状态！
 * 中介类是观察者模式的执行类。一个观察者的一个识标下只有一个实例；
 *
 * @param <TCommandKey>  命令枚举类型
 * @param <TMediatorKey> 中介枚举类型
 */

public abstract class Mediator<TCommandKey, TMediatorKey> {

    /**
     * 将中介进行分组，同组的中介只会有一个在激活状态
     */
    protected Object group;
    protected TMediatorKey type;
    protected Zero<TCommandKey, TMediatorKey> zero;
    protected abstract void init();
    protected List<Proxy> pool;

    /**
     * 中介类！
     *
     * @param group 将中介进行分组的识标建议使用枚举，同组的中介只会有一个在激活状态
     */
    public Mediator(Object group) {
        this.group = group;
        pool = new ArrayList<Proxy>();
    }

    /**
     * 中介类！
     */
    public Mediator() {
        pool = new ArrayList<Proxy>();
    }

    /**
     * 获取数据代理
     *
     * @param proxyClass 所要获取数据代理类的反射对象(如 Object.class)
     * @param <TProxy>   数据代理类的类型
     * @return 获取到的数据代理类
     */
    public <TProxy extends Proxy> TProxy getProxy(Class<TProxy> proxyClass) {
        return zero.model.getProxy(proxyClass);
    }


    public void execute(Boolean b){
        if (b){
            _activate();
        }else
        {
            _inactivate();
        }
    }



    private void _activate() {
        if (group != null) {
            if (zero.mediatorKeyGroup.containsKey(group)) {
                if (zero.mediatorKeyGroup.get(group) != type) {
                    zero.inactivate(zero.mediatorKeyGroup.get(group));
                    zero.mediatorKeyGroup.put(group, type);
                }
            } else {
                zero.mediatorKeyGroup.put(group, type);
            }
        }
        for (Proxy proxy : pool) {
            proxy.bind(this);
        }
        activate();
    }

    private void _inactivate() {
        for (Proxy proxy : pool) {
            proxy.unbind(this);
        }
        if (group != null && zero.mediatorKeyGroup.containsKey(group) && zero.mediatorKeyGroup.get(group) != type) {
            zero.mediatorKeyGroup.remove(type);
        }
        inactivate();
    }

    /**
     * 初始化
     *
     * @param zero 对主类的引用
     * @param type 实例化对应识标
     */
    public void init(Object zero, Object type) {
        this.zero = (Zero<TCommandKey, TMediatorKey>) zero;
        this.type = (TMediatorKey) type;
        init();
    }

    /**
     * 派发命令
     *
     * @param key  派发的命令识标
     * @param args 派发的数据对象
     */
    public void command(TCommandKey key, Object... args) {
        zero.command(key, args);
    }

    /**
     * 添加所要关注的数据代理！
     * 当数据代理进行更新时会执行 子类的update;
     * updtae方法 支持重载
     *
     * @param proxy 添加所要关注的数据代理！
     */
    public void addProxy(Proxy proxy) {
//        proxy.bind(this);
        pool.add(proxy);
    }

    /**
     * 删除所要关注的数据代理！
     * 当数据代理进行更新时会执行 子类的update;
     * updtae方法 支持重载
     *
     * @param proxy 删除所要关注的数据代理！
     */
    public void removeProxy(Proxy proxy) {
        pool.remove(proxy);
    }

    /**
     * 释放
     * 移除观察者中介类的实例的引用！
     * 释放不会立即生效！
     */
    public void dispose() {
        zero.inactivate(type);
        zero.view.dispose(type);
    }

    /**
     * 需要实现 当中介类激活时所要处理的逻辑
     */
    protected abstract void activate();


    /**
     * 需要实现 当所关注的数据代理更新时所要处理的逻辑
     * 数据代理 支持重载
     *
     * @param proxy 添加所要关注的数据代理！
     */
    protected abstract void update(Proxy proxy);

    /**
     * 需要实现 当中介类灭活时所要处理的逻辑
     */
    protected abstract void inactivate();
}

package com.lime.zeromvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据代理类
 */
public class Proxy {

    private List<Mediator> pool;

    public Proxy() {
        pool = new ArrayList<Mediator>();
    }

    /**
     * 更新数据，用于通知中介
     */
    public void update() {
        for (Mediator mediator : pool) {
            try {
                Method method = mediator.getClass().getMethod("update", this.getClass());
                method.invoke(mediator, this);
            } catch (NoSuchMethodException e) {
                mediator.update(this);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 绑定中介！中介关注时调用的方法
     * 不需要手机调用
     *
     * @param mediator
     */
    public void bind(Mediator mediator) {
        pool.add(mediator);
    }

    /**
     * 解除绑定中介！
     * 不需要手机调用
     *
     * @param mediator
     */
    public void unbind(Mediator mediator) {
        pool.remove(mediator);
    }

}

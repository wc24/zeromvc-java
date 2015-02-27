package com.lime.zeromvc;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.reflect.Method;

/**
 * 反射式 观察者
 * 在观察者对象上添加事件标识和对应要执行的类，一个标识可以对应多个类，一个类可以添加进不对的标识。当观察者派发通知时 通知标识所对应的类所会被实例化，初始化并且执行execute方法！若执行释放，第二接收到相应通知将会构建新的对象！否则只会产生一个识标只会产生一个实例并且常在内存！
 *
 * @param <TKey>
 */
public class Observer<TKey> {

    protected Object target;
    protected Map<TKey, List<Class>> pool;
    protected Map<TKey, Map<Class, Object>> instancePool;

    /**
     * 观察者
     */
    public Observer() {
        this.target = this;
        init();
    }

    /**
     * 观察者
     *
     * @param target 派发通知时的目标对象
     */
    public Observer(Object target) {
        this.target = target;
        init();
    }

    protected void init() {
        pool = new HashMap<TKey, List<Class>>();
        instancePool = new HashMap<TKey, Map<Class, Object>>();
    }

    /**
     * 添加监听
     *
     * @param type      监听标识
     * @param classType 监听执行类的反射对象(如 Object.class)
     * @return
     */
    public boolean addListener(TKey type, Class classType) {
        Boolean out = false;
        if (!hasListener(type)) {
            pool.put(type, new ArrayList<Class>());
            instancePool.put(type, new HashMap<Class, Object>());
        }
        if (!hasListener(type, classType)) {
            pool.get(type).add(classType);
            out = true;
        }
        return out;
    }

    /**
     * 移除监听
     *
     * @param type      监听标识
     * @param classType 监听执行类的反射对象(如 Object.class)
     * @return
     */
    public boolean removeListener(TKey type, Class classType) {
        Boolean out = false;
        if (hasListener(type)) {
            out = pool.get(type).remove(classType);
        }
        return out;
    }

    /**
     * 消除标识下所有监听
     *
     * @param type 监听标识
     * @return 是否消除成功
     */
    public boolean clearListener(TKey type) {
        Boolean out = false;
        if (hasListener(type)) {
            pool.get(type).clear();
            pool.remove(type);
            out = true;
        }
        return out;
    }

    /**
     * 判断标识下是否存在监听
     *
     * @param type 监听标识
     * @return
     */
    public boolean hasListener(TKey type) {
        return pool.containsKey(type);
    }

    /**
     * 判断标识下是否存在监听
     *
     * @param type      监听标识
     * @param classType 监听执行类的反射对象(如 Object.class)
     * @return 是否存在监听
     */
    public boolean hasListener(TKey type, Class classType) {
        return pool.containsKey(type) && pool.get(type).contains(classType);
    }

    /**
     * 释放
     *
     * @param type 监听标识
     * @return
     */
    public boolean dispose(TKey type) {
        if (instancePool.containsKey(type)) {
            instancePool.remove(type);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通知
     *
     * @param type 监听标识
     * @param method 调用方法名
     * @param data 监听标识
     * @return 执行次数
     */
    public int notify(TKey type,String method,Object data) {
        if (null == method || method==""){
            method="execute";
        }
        int out = 0;
        if (hasListener(type)) {
            for (Class classType : pool.get(type)) {
                Object neure;
                if (instancePool.containsKey(type) && instancePool.get(type).containsKey(classType)) {
                    neure = instancePool.get(type).get(classType);
                    executeInvoke(classType,neure,method,data);
                } else try {
                    neure = classType.newInstance();
                    instancePool.get(type).put(classType, neure);
                    Method initMethod;
                    try {
                        initMethod=classType.getMethod("init", target.getClass(), type.getClass());
                        initMethod.invoke(neure,target, type);
                    } catch (NoSuchMethodException e) {
                        try {
                            initMethod=classType.getMethod("init", Object.class, Object.class);
                            initMethod.invoke(neure,target, type);
                        } catch (NoSuchMethodException e1) {
                        } catch (InvocationTargetException e1) {
                            e1.printStackTrace();
                        }
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    executeInvoke(classType,neure,method,data);
                    out++;
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return out;
    }
    private void executeInvoke(Class classType, Object neure, String method, Object data) {
        Method executeMethod;
        try {
            if (data==null){
                executeMethod=classType.getMethod(method);
                executeMethod.invoke(neure);
            }else {
                System.out.println(data.getClass().getName());
                try {
                    executeMethod=classType.getMethod(method, data.getClass());
                } catch (NoSuchMethodException e) {
                    executeMethod=classType.getMethod(method, Object.class);
                }
                executeMethod.invoke(neure,data);
            }
        } catch (NoSuchMethodException e) {
            System.out.println("ZeroMvcErr: " + classType.getName() + " no " + method);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通知
     *
     * @param type 监听标识
     * @return 执行次数
     */
    public int notify(TKey type,String method) {
        return notify(type,method,null);
    }
    /**
     * 通知
     *
     * @param type 监听标识
     * @return 执行次数
     */
    public int notify(TKey type) {
        return notify(type,null,null);
    }
}

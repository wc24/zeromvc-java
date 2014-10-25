package com.lime.zeromvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spine<TKey> {

    protected final Object target;
    protected Map<TKey, List<Class<Neure>>> pool;
    protected Map<TKey, Neure> instancePool;


    public Spine() {
        this.target = this;
        init();
    }

    public Spine(Object target) {
        this.target = target;
        init();
    }

    protected void init() {
        pool = new HashMap<TKey, List<Class<Neure>>>();
        instancePool = new HashMap<TKey, Neure>();
    }

    public boolean addListener(TKey type, Class<Neure> classType) {
        Boolean out = false;
        if (!hasListener(type)) {
            pool.put(type, new ArrayList<Class<Neure>>());
        }
        if (!hasListener(type, classType)) {
            pool.get(type).add(classType);
            out = true;
        }
        return out;
    }

    public boolean removeListener(TKey type, Object classType) {
        Boolean out = false;
        if (hasListener(type)) {
            out = pool.get(type).remove(classType);
        }
        return out;
    }

    public boolean clearListener(TKey type) {
        Boolean out = false;
        if (hasListener(type)) {
            pool.get(type).clear();
            pool.remove(type);
            out = true;
        }
        return out;
    }

    public boolean hasListener(TKey type) {
        return pool.containsKey(type);
    }

    public boolean hasListener(TKey type, Class<Neure> classType) {
        if (Neure.class.isAssignableFrom(classType)) {
            return pool.containsKey(type) && pool.get(type).contains(classType);
        } else {
            System.out.println("[LIME] classType must be IObserverExecute subclasses ");
            return false;
        }
    }

    public boolean release(TKey type) {
        if (instancePool.containsKey(type)) {
            instancePool.remove(type);
            return true;
        } else {
            return false;
        }
    }

    public int notify(TKey type, Object data) {
        int out = 0;
        if (hasListener(type)) {
            for (Class<Neure> classType : pool.get(type)) {
                Neure neure;
                if (instancePool.containsKey(type)) {
                    neure = instancePool.get(type);
                    neure.execute(data);
                } else try {
                    neure = classType.newInstance();
                    neure.init(target, type);
                    neure.execute(data);
                    instancePool.put(type, neure);
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

    public int notify(TKey type) {
        return notify(type, null);
    }
}

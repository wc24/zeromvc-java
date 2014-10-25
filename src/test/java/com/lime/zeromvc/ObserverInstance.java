package com.lime.zeromvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObserverInstance{

    private Map<Object, List<Neure>> pool;
    private Object target;


    public ObserverInstance() {
        this.target = this;
        pool = new HashMap<Object, List<Neure>>();
    }

    public ObserverInstance(Object target) {
        this.target = target;
        pool = new HashMap<Object, List<Neure>>();
    }
    public boolean addListener(Object type, Neure notifier) {
        Boolean out = false;
        if (!hasListener(type)) {
            pool.put(type, new ArrayList<Neure>());
        }
        if (!hasListener(type, notifier)) {
            pool.get(type).add(notifier);
            out = true;
        }
        return out;
    }

    public boolean removeListener(Object type, Neure notifier) {
        Boolean out = false;
        if (hasListener(type)) {
            out = pool.get(type).remove(notifier);
        }
        return out;
    }

    public boolean clearListener(Object type) {
        Boolean out = false;
        if (hasListener(type)) {
            pool.get(type).clear();
            pool.remove(type);
            out = true;
        }
        return out;
    }

    public boolean hasListener(Object type) {
        return pool.containsKey(type);
    }

    public boolean hasListener(Object type, Neure notifier) {
        return pool.containsKey(type) && pool.get(type).contains(notifier);
    }

    public int notify(Object type, Object data) {
        if (hasListener((Object) type)) {
            for (Neure notifier : pool.get(type)) {
                //notifier.execute(target, type, data);
            }
            return pool.get(type).size();
        } else {
            return 0;
        }
    }

    public int notify(Object type) {
        return notify(type, null);
    }
}

package com.lime.zeromvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Proxy {

    private List<Mediator> pool;

    public Proxy() {
        pool = new ArrayList<Mediator>();
    }

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

    public void bind(Mediator mediator) {
        pool.add(mediator);
    }

    public void unbind(Mediator mediator) {
        pool.remove(mediator);
    }

}

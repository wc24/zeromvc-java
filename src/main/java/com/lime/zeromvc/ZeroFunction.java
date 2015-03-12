package com.lime.zeromvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by linming on 15-3-1.
 */
public class ZeroFunction {
    public Method method;
    public Class owner;
    public String methodName;

    public ZeroFunction(Class owner, String methodName) {
        this.owner = owner;
        this.methodName = methodName;
        this.method = null;
        String internedName = methodName.intern();
        for (Method method : owner.getMethods()) {
            if (method.getName() == internedName) {
                this.method = method;
                break;
            }
        }
        if (method == null) {
            System.out.print("[ZeroMvcErrors]------------>");
            System.out.println(methodName + " No Such Method");
        }
    }
    public void call(Object instance, Object... args) throws InvocationTargetException, IllegalAccessException {
        if (args.length==0){
            method.invoke(instance);
        }else {
            method.invoke(instance, args);
        }
    }
}
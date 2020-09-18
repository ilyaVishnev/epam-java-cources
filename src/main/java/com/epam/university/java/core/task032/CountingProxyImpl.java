package com.epam.university.java.core.task032;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CountingProxyImpl implements CountingProxy {

    private Map<String, Integer> methodsCount = new HashMap<>();
    private SomeActionExecutor someActionExecutor;

    public CountingProxyImpl(SomeActionExecutor someActionExecutor) {
        this.someActionExecutor = someActionExecutor;
    }

    @Override
    public int getInvocationsCount(String methodName) {
        if (methodsCount.get(methodName) != null) {
            return methodsCount.get(methodName);
        }
        return 0;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodsCount.get(method.getName()) != null) {
            methodsCount.put(method.getName(), methodsCount.get(method.getName()) + 1);
        } else {
            methodsCount.put(method.getName(), 1);
        }
        return method.invoke(this.someActionExecutor, args);
    }
}

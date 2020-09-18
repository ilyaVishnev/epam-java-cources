package com.epam.university.java.core.task032;


import java.lang.reflect.Proxy;

public class Task032Impl implements Task032 {

    private SomeActionExecutor someActionExecutor = new SomeActionExecutorImpl();

    /**
     * Create proxy wrapper.
     *
     * @return proxy instance
     */
    @Override
    public CountingProxy createProxyWrapper() {
        return new CountingProxyImpl(someActionExecutor);
    }

    /**
     * Create action executor with given proxy instance.
     *
     * @param proxy proxy instance
     * @return action executor instance
     */
    @Override
    public SomeActionExecutor createExecutorWithProxy(CountingProxy proxy) {
        SomeActionExecutor executor = (SomeActionExecutor) Proxy
                .newProxyInstance(someActionExecutor.getClass().getClassLoader(),
                        someActionExecutor.getClass().getInterfaces(), proxy);
        return executor;
    }
}

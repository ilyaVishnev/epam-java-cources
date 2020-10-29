package com.epam.university.java.project.core.cdi.context;

import com.epam.university.java.project.core.cdi.bean.BeanDefinitionReader;
import com.epam.university.java.project.core.cdi.bean.BeanFactory;
import com.epam.university.java.project.core.cdi.bean.BeanFactoryImpl;
import com.epam.university.java.project.core.cdi.io.Resource;

import java.util.Collection;

public class ApplicationContextImpl implements ApplicationContext {

    private BeanFactoryImpl factory;
    private BeanDefinitionReader reader;

    public ApplicationContextImpl(BeanDefinitionReader reader,
                                  BeanFactoryImpl factory) {
        this.reader = reader;
        this.factory = factory;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {
        return reader.loadBeanDefinitions(resource);
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return reader.loadBeanDefinitions(resources);
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return factory.getBean(beanClass);
    }

    @Override
    public Object getBean(String beanName) {
        return factory.getBean(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        T t = factory.getBean(beanName, beanClass);
        if (factory.isRuntimeExc()) {
            throw new RuntimeException();
        }
        return t;
    }
}

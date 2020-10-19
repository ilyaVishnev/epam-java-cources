package com.epam.university.java.project.core.cdi.context;

import com.epam.university.java.project.core.cdi.bean.BeanDefinitionReader;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionReaderImpl;
import com.epam.university.java.project.core.cdi.bean.BeanFactory;
import com.epam.university.java.project.core.cdi.bean.BeanFactoryImpl;

public class ApplicationContextFactoryImpl implements ApplicationContextFactory {
    @Override
    public ApplicationContext newInstance() {
        BeanDefinitionReaderImpl reader = new BeanDefinitionReaderImpl();
        BeanFactory factory = new BeanFactoryImpl(reader);
        ApplicationContext context = new ApplicationContextImpl(reader, factory);
        return context;
    }
}

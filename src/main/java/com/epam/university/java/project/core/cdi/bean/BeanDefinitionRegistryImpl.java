package com.epam.university.java.project.core.cdi.bean;


import java.util.ArrayList;
import java.util.List;

public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {

    private List<BeanDefinition> beans = new ArrayList<>();

    public BeanDefinitionRegistryImpl(BeanDefinitionReaderImpl reader) {
        this.beans = new ArrayList<>(reader.getBeanDefinitions());
    }

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        beans.add(definition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        for (BeanDefinition bean : beans) {
            if (bean.getId().equals(beanId)) {
                return bean;
            }
        }
        return null;
    }
}

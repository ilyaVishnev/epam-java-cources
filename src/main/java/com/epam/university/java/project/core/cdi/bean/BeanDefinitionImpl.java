package com.epam.university.java.project.core.cdi.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeanDefinitionImpl implements BeanDefinition {

    private String id;
    private String className;
    private List<BeanPropertyDefinition> properties = new ArrayList<>();
    private String postConstruct;
    private String scope;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public Collection<BeanPropertyDefinition> getProperties() {
        return properties;
    }

    @Override
    public void setProperties(Collection<BeanPropertyDefinition> properties) {
        this.properties = new ArrayList<>(properties);
    }

    @Override
    public String getPostConstruct() {
        return this.postConstruct;
    }

    @Override
    public void setPostConstruct(String methodName) {
        this.postConstruct = methodName;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }
}
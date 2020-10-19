package com.epam.university.java.project.core.cdi.bean;


import java.io.File;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.service.BookDao;
import com.epam.university.java.project.service.BookDaoXmlImpl;
import com.epam.university.java.project.service.StateMachineManagerImpl;


public class BeanFactoryImpl implements BeanFactory {

    private BeanDefinitionReaderImpl reader;
    private String path = System.getProperty("user.dir") + File.separator
            + "build" + File.separator + "classes" + File.separator
            + "java" + File.separator + "test" + File.separator + "com"
            + File.separator + "epam" + File.separator + "university"
            + File.separator + "java" + File.separator
            + "project" + File.separator + "core" + File.separator
            + "cdi" + File.separator + "context";
    private String pathStructure = System.getProperty("user.dir")
            + File.separator
            + "build" + File.separator + "classes" + File.separator
            + "java"
            + File.separator + "test" + File.separator + "com"
            + File.separator + "epam" + File.separator + "university"
            + File.separator + "java" + File.separator
            + "project" + File.separator + "core" + File.separator + "cdi"
            + File.separator + "structure";
    private URLClassLoader loader1;
    private Map cash = new HashMap<>();

    /**
     * constructor.
     *
     * @param reader for reading xml
     */
    public BeanFactoryImpl(BeanDefinitionReaderImpl reader) {
        this.reader = reader;
        File file = new File(path);
        File file1 = new File(pathStructure);
        try {
            URL[] classUrl = new URL[]{file.toURI().toURL(), file1.toURI().toURL()};
            loader1 = new URLClassLoader(classUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> beanClass) {
        List<BeanDefinition> beans = reader.getBeanDefinitions();
        Field[] fields = beanClass.getDeclaredFields();
        for (BeanDefinition bean : beans) {
            try {
                Class<?> myClass = loader1.loadClass(bean.getClassName());
                List<Class<?>> interfaces = Arrays.asList(myClass.getInterfaces());
                if (interfaces.contains(beanClass) && beanClass.isInterface()) {
                    Object newObj = myClass.getDeclaredConstructor().newInstance();
                    return (T) getBean(newObj.getClass());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (bean.getClassName().equals(beanClass.getCanonicalName())) {
                List<BeanPropertyDefinition> property =
                        new ArrayList<>(bean.getProperties());
                T t = null;
                try {
                    t = beanClass.getDeclaredConstructor().newInstance();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        switch (field.getName()) {
                            case "property1":
                                field.set(t, property.get(0).getValue());
                                break;
                            case "property2":
                                field.set(t, Integer.parseInt(property.get(1)
                                        .getValue()));
                                break;
                            case "childBean":
                                field.set(t, getBean("childBean"));
                                break;
                            case "singletonBean":
                                field.set(t, getBean("singletonBean"));
                                break;
                            case "stringCollection":
                                ListDefinition listDefinition =
                                        (ListDefinition) bean.getProperties().stream()
                                                .filter(p -> p.getName()
                                                        .equals("stringCollection")).findFirst()
                                                .get()
                                                .getData();
                                Collection<String> stringCollection =
                                        listDefinition.getItems().stream()
                                                .map(l -> l.getValue())
                                                .collect(Collectors.toList());
                                field.set(t, stringCollection);
                                break;
                            case "stringMap":
                                MapDefinition mapDefinition =
                                        (MapDefinition) bean.getProperties().stream()
                                                .filter(p -> p.getName()
                                                        .equals("stringMap")).findFirst().get()
                                                .getData();
                                Map<String, String> stringMap =
                                        mapDefinition.getValues().stream()
                                                .collect(Collectors
                                                        .toMap(k -> k.getKey(), v -> v.getValue()));
                                field.set(t, stringMap);
                                break;
                            case "objectMap":
                                MapDefinition mapDefinitionObj =
                                        (MapDefinition) bean.getProperties().stream()
                                                .filter(p -> p.getName()
                                                        .equals("objectMap"))
                                                .findFirst().get()
                                                .getData();
                                Map<String, Object> objectMap =
                                        mapDefinitionObj.getValues().stream()
                                                .collect(Collectors
                                                        .toMap(k -> k.getKey(), v -> getBeanFromId(v
                                                                .getRef())));
                                field.set(t, objectMap);
                                break;
                            case "bookDao":
                                BookDao bookDao = null;
                                bookDao =
                                        (BookDaoXmlImpl) getObjectByRef("bookDao", bean, beans);
                                field.set(t, bookDao);
                                break;
                            case "machineManager":
                                StateMachineManager machineManager = null;
                                machineManager =
                                        (StateMachineManagerImpl) getObjectByRef("machineManager",
                                                bean, beans);
                                field.set(t, machineManager);
                                break;
                            default:
                                break;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (cash.get(bean.getId()) == null) {
                    if (bean.getId().equals("statelessBean")
                            || bean.getId()
                            .equals("statelessBeanWithDependencies")) {
                        return t;
                    }
                    cash.put(bean.getId(), t);
                }
                return (T) cash.get(bean.getId());
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object getBean(String beanName) {
        Object result = null;
        try {
            List<BeanDefinition> beans = reader.getBeanDefinitions();
            String beanNameG = beanName.substring(0, 1).toUpperCase()
                    + beanName.substring(1);
            String className = beanNameG.equals("ComplexObject")
                    || beanNameG.equals("DependentObject")
                    ?
                    "com.epam.university.java.project.core.cdi.structure.ComplexObject"
                    : "com.epam.university.java.project.core.cdi.context."
                    + beanNameG;
            Class<?> t = loader1.loadClass(className);
            result = t.getDeclaredConstructor().newInstance();
            for (BeanDefinition bean : beans) {
                if (bean.getId().equals(beanName)) {
                    List<BeanPropertyDefinition> property =
                            new ArrayList<>(bean.getProperties());
                    Field[] fields = t.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        switch (field.getName()) {
                            case "property1":
                                field.set(result, property.get(0).getValue());
                                break;
                            case "property2":
                                field.set(result, Integer.parseInt(property.get(1)
                                        .getValue()));
                                break;
                            case "childBean":
                                field.set(result, getBean("childBean"));
                                break;
                            case "singletonBean":
                                field.set(result, getBean("singletonBean"));
                                break;
                            case "stringCollection":
                                ListDefinition listDefinition = (ListDefinition) bean
                                        .getProperties().stream()
                                        .filter(p -> p.getName().equals("stringCollection"))
                                        .findFirst().get()
                                        .getData();
                                Collection<String> stringCollection = listDefinition
                                        .getItems().stream()
                                        .map(l -> l.getValue())
                                        .collect(Collectors.toList());
                                field.set(t, stringCollection);
                                break;
                            case "stringMap":
                                MapDefinition mapDefinition = (MapDefinition) bean
                                        .getProperties().stream()
                                        .filter(p -> p.getName()
                                                .equals("stringMap")).findFirst()
                                        .get()
                                        .getData();
                                Map<String, String> stringMap = mapDefinition
                                        .getValues().stream()
                                        .collect(Collectors
                                                .toMap(k -> k.getKey(), v -> v.getValue()));
                                field.set(t, stringMap);
                                break;
                            case "objectMap":
                                MapDefinition mapDefinitionObj = (MapDefinition) bean
                                        .getProperties().stream()
                                        .filter(p -> p.getName().equals("objectMap"))
                                        .findFirst().get()
                                        .getData();
                                Map<String, Object> objectMap = mapDefinitionObj
                                        .getValues().stream().collect(Collectors
                                                .toMap(k -> k.getKey(), v -> getBeanFromId(v
                                                        .getRef())));
                                field.set(t, objectMap);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
        if (cash.get(beanName) == null) {
            if (beanName.equals("statelessBean")
                    || beanName.equals("statelessBeanWithDependencies")) {
                return result;
            }
            cash.put(beanName, result);
        }
        return cash.get(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return getBean(beanClass);
    }

    private Object getBeanFromId(String id) {
        Object result = null;
        try {
            List<BeanDefinition> beans = reader.getBeanDefinitions();
            for (BeanDefinition bean : beans) {
                if (bean.getId().equals(id)) {
                    List<BeanPropertyDefinition> property =
                            new ArrayList<>(bean.getProperties());
                    Class<?> t = loader1.loadClass(bean.getClassName());
                    result = t.getDeclaredConstructor().newInstance();
                    Field[] fields = t.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        switch (field.getName()) {
                            case "property1":
                                field.set(result, property.get(0).getValue());
                                break;
                            case "property2":
                                field.set(result, Integer.parseInt(property.get(1).getValue()));
                                break;
                            case "childBean":
                                field.set(result, getBean("childBean"));
                                break;
                            case "singletonBean":
                                field.set(result, getBean("singletonBean"));
                                break;
                            case "stringCollection":
                                Optional<BeanPropertyDefinition> definitionStringCol = bean
                                        .getProperties().stream()
                                        .filter(p -> p.getName().equals("stringCollection"))
                                        .findFirst();
                                if (definitionStringCol.isPresent()) {
                                    ListDefinition listDefinition =
                                            (ListDefinition) definitionStringCol
                                                    .get()
                                                    .getData();
                                    Collection<String> stringCollection = listDefinition.getItems()
                                            .stream()
                                            .map(l -> l.getValue()).collect(Collectors.toList());
                                    field.set(t, stringCollection);
                                }
                                break;
                            case "stringMap":
                                Optional<BeanPropertyDefinition> definitionStrMap = bean
                                        .getProperties().stream()
                                        .filter(p -> p.getName().equals("stringMap"))
                                        .findFirst();
                                if (definitionStrMap.isPresent()) {
                                    MapDefinition mapDefinition = (MapDefinition) definitionStrMap
                                            .get()
                                            .getData();
                                    Map<String, String> stringMap = mapDefinition.getValues()
                                            .stream()
                                            .collect(Collectors.toMap(k -> k.getKey(), v -> v
                                                    .getValue()));
                                    field.set(t, stringMap);
                                }
                                break;
                            case "objectMap":
                                Optional<BeanPropertyDefinition> definitionObjMap = bean
                                        .getProperties().stream()
                                        .filter(p -> p.getName().equals("objectMap"))
                                        .findFirst();
                                if (definitionObjMap.isPresent()) {
                                    MapDefinition mapDefinitionObj
                                            = (MapDefinition) definitionObjMap
                                            .get()
                                            .getData();
                                    Map<String, Object> objectMap = mapDefinitionObj
                                            .getValues().stream()
                                            .collect(Collectors.toMap(k -> k.getKey(), v ->
                                                    getBeanFromId(v.getRef())));
                                    field.set(t, objectMap);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
        return result;
    }

    private Object getObjectByRef(String name, BeanDefinition bean,
                                  List<BeanDefinition> beans) {
        Object result = null;
        try {
            Optional<BeanPropertyDefinition> propertyDefinition = bean
                    .getProperties().stream()
                    .filter(p -> p.getName().equals(name)).findFirst();
            if (propertyDefinition.isPresent()) {
                Optional<BeanDefinition> definition = beans.stream()
                        .filter(n -> n.getId()
                                .equals(propertyDefinition.get().getRef()))
                        .findFirst();
                if (definition.isPresent()) {
                    Class<?> bookDaoClass = loader1
                            .loadClass(definition.get()
                                    .getClassName());
                    result = bookDaoClass.getDeclaredConstructor().newInstance();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}

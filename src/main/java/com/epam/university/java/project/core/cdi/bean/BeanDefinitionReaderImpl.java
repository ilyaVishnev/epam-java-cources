package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class BeanDefinitionReaderImpl implements BeanDefinitionReader {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    @Override
    public int loadBeanDefinitions(Resource resource) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        BeanHandlerImpl handler = new BeanHandlerImpl();
        try {
            SAXParser parser = factory.newSAXParser();
            File file = resource.getFile();
            parser.parse(resource.getFile(), handler);
            beanDefinitions = handler.getBeans();
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
        return beanDefinitions.size();
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        int countBeanDefinitions = 0;
        for (Resource resource : resources) {
            countBeanDefinitions += loadBeanDefinitions(resource);
        }
        return countBeanDefinitions;
    }

    public List<BeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }
}

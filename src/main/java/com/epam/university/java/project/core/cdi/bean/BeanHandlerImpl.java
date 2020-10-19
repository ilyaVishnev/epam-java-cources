package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.core.task034.Person;
import com.epam.university.java.core.task034.PersonImpl;
import com.epam.university.java.core.task034.PhoneNumber;
import com.epam.university.java.core.task034.PhoneNumberImpl;
import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.ListDefinitionImpl;
import com.epam.university.java.project.core.cdi.structure.ListDefinitionImpl.ListItemDefinitionImpl;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinitionImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class BeanHandlerImpl extends DefaultHandler {


    private String id;
    private List<BeanDefinition> beans = new ArrayList<>();
    private BeanDefinition bean = new BeanDefinitionImpl();
    private List<BeanPropertyDefinition> properties = new ArrayList<>();
    private BeanPropertyDefinition property;
    private String currentqName;
    private List<ListDefinition.ListItemDefinition> listItemDefinitions = new ArrayList<>();
    private List<MapDefinition.MapEntryDefinition> mapEntryDefinitions = new ArrayList<>();
    private MapDefinition.MapEntryDefinition mapEntryDefinition;
    private String attributeName = "";

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentqName = qName;
        if (attributes.getValue("id") != null) {
            bean.setId(attributes.getValue("id"));
            bean.setClassName(attributes.getValue("class"));
            bean.setScope(attributes.getValue("scope"));
            id = attributes.getValue("id");
            beans.add(bean);
            if (id.equals("parentBean")) {
                bean.setPostConstruct(attributes.getValue("init"));
            }
        }
        if (qName.equals("property")) {
            property = new BeanPropertyDefinitionImpl();
            listItemDefinitions = new ArrayList<>();
            mapEntryDefinitions = new ArrayList<>();
            property.setName(attributes.getValue("name"));
            if (attributes.getValue("value") != null) {
                property.setValue(attributes.getValue("value"));
            }
            if (attributes.getValue("ref") != null) {
                property.setRef(attributes.getValue("ref"));
            }
            attributeName = attributes.getValue("name");
        }
        if (qName.equals("entry")) {
            mapEntryDefinition = new MapDefinitionImpl.MapEntryDefinitionImpl();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("bean")) {
            bean.setProperties(properties);
            properties = new ArrayList<>();
            bean = new BeanDefinitionImpl();
        }
        if (qName.equals("property")) {
            properties.add(property);
            if (attributeName.equals("stringCollection")) {
                ListDefinition listDefinition = new ListDefinitionImpl();
                listDefinition.setItems(new ArrayList<>(listItemDefinitions));
                property.setData(listDefinition);
            }
            if (attributeName.equals("stringMap")
                    || attributeName.equals("objectMap")) {
                MapDefinition mapDefinition = new MapDefinitionImpl();
                mapDefinition.setValues(new ArrayList<>(mapEntryDefinitions));
                property.setData(mapDefinition);
            }
            attributeName = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentqName.equals("key")) {
            mapEntryDefinition = new MapDefinitionImpl.MapEntryDefinitionImpl();
            mapEntryDefinition.setKey(new String(ch, start, length));
            mapEntryDefinitions.add(mapEntryDefinition);
        }
        if (currentqName.equals("value")) {
            if (property.getName().equals("stringCollection")) {
                ListDefinition.ListItemDefinition listItemDefinition = new ListItemDefinitionImpl();
                listItemDefinition.setValue(new String(ch, start, length));
                listItemDefinitions.add(listItemDefinition);
            } else {
                mapEntryDefinition.setValue(new String(ch, start, length));
            }
        }
        if (currentqName.equals("ref")) {
            mapEntryDefinition.setRef(new String(ch, start, length));
        }
        currentqName = "";
    }

    public List<BeanDefinition> getBeans() {
        return beans;
    }
}

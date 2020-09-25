package com.epam.university.java.core.task034;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

public class SaxHandlerImpl extends SaxHandler {

    private String lastElement;
    private String information;
    private Person person = new PersonImpl();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        lastElement = qName;
        if (lastElement.equals("person")) {
            person.setId(Integer.parseInt(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (lastElement.equals("person-phone")) {
            PhoneNumber phoneNumber = new PhoneNumberImpl();
            phoneNumber.setPhoneNumber(information);
            person.getPhoneNumbers().add(phoneNumber);
        }
        if (lastElement.equals("first-name")) {
            person.setFirstName(information);
        }
        if (lastElement.equals("last-name")) {
            person.setLastName(information);
        }
        lastElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        information = new String(ch, start, length);
    }

    public Person getPerson() {
        return person;
    }
}

package com.epam.university.java.core.task034;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class Task034Impl implements Task034 {
    @Override
    public Person readWithSaxParser(DefaultHandler handler, String filepath) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Person person = null;
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(getClass().getResource(filepath)
                    .getFile()), handler);
            person = ((SaxHandlerImpl) handler).getPerson();
        } catch (Exception ex) {
            System.out.println();
        }
        return person;
    }

    @Override
    public Person readWithJaxbParser(String filepath) {
        Person person = null;
        try {
            URI fileUri = getClass().getResource(filepath).toURI();
            JAXBContext context = JAXBContext.newInstance(PersonImpl.class);
            Unmarshaller um = context.createUnmarshaller();
            person = (Person) um.unmarshal(new File(fileUri));
        } catch (JAXBException | URISyntaxException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public Person readWithStaxParser(XMLStreamReader streamReader) {
        Person person = null;
        String information = "";
        try {
            int event = streamReader.getEventType();
            while (true) {
                switch (event) {
                    case XMLStreamConstants
                            .START_ELEMENT:
                        if (streamReader.getLocalName().equals("person")) {
                            person = new PersonImpl();
                            person.setId(Integer.parseInt(streamReader.getAttributeValue(0)));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (streamReader.getLocalName().equals("first-name")) {
                            person.setFirstName(information);
                        }
                        if (streamReader.getLocalName().equals("last-name")) {
                            person.setLastName(information);
                        }
                        if (streamReader.getLocalName().equals("person-phone")) {
                            PhoneNumber phoneNumber = new PhoneNumberImpl();
                            phoneNumber.setPhoneNumber(information);
                            person.getPhoneNumbers().add(phoneNumber);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        information = streamReader.getText();
                        break;
                    default:
                        break;
                }
                if (!streamReader.hasNext()) {
                    break;
                }
                event = streamReader.next();

            }
        } catch (Exception ex) {
            System.out.println();
        }
        return person;
    }
}

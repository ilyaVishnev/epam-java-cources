package com.epam.university.java.core.task034;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(name = "person")
public class PersonImpl implements Person {

    private int id;
    private String firstName;
    private String lastName;
    private Collection<PhoneNumber> phoneNumbers = new ArrayList<>();
    private List<String> phones = new ArrayList<>();

    public PersonImpl() {
    }

    @XmlAttribute(name = "id")
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "first-name")
    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "last-name")
    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElementWrapper(name = "person-phones")
    @XmlElement(name = "person-phone", type = PhoneNumberImpl.class)
    @Override
    public Collection<PhoneNumber> getPhoneNumbers() {
        if (phoneNumbers.isEmpty()) {
            Iterator<String> iterator = phones.iterator();
            while (iterator.hasNext()) {
                PhoneNumber phoneNumber = new PhoneNumberImpl();
                phoneNumber.setPhoneNumber(iterator.next());
                phoneNumbers.add(phoneNumber);
            }
        }
        return phoneNumbers;
    }

    @Override
    public void setPhoneNumbers(Collection<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}

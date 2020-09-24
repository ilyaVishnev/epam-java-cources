package com.epam.university.java.core.task034;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PersonImpl implements Person {

    private int id;
    private String firstName;
    private String lastName;
    private Collection<PhoneNumber> phoneNumbers = new ArrayList<>();
    private List<String> phones = new ArrayList<>();

    public PersonImpl() {
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
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

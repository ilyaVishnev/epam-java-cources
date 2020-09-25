package com.epam.university.java.core.task034;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.util.Objects;

@XmlType(name = "person-phone")
public class PhoneNumberImpl implements PhoneNumber {

    private String phoneNumber;

    public PhoneNumberImpl() {
    }

    @Override
    @XmlValue
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneNumberImpl that = (PhoneNumberImpl) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}

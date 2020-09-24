package com.epam.university.java.core.task034;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


public class PhoneNumberImpl implements PhoneNumber {

    private String phoneNumber;

    public PhoneNumberImpl(){}

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

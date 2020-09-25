package com.epam.university.java.core.task034;

import javax.xml.bind.annotation.XmlType;

/**
 * Phone number model.
 */
public interface PhoneNumber {
    /**
     * Get phone number value.
     * @return number value
     */
    String getPhoneNumber();

    /**
     * Set phone number value.
     * @param phoneNumber number value
     */
    void setPhoneNumber(String phoneNumber);
}

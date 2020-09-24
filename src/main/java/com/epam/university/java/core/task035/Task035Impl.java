package com.epam.university.java.core.task035;

import com.epam.university.java.core.task034.Person;
import com.epam.university.java.core.task034.PersonImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.StringReader;
import java.util.List;

public class Task035Impl implements Task035 {
    @Override
    public Person readWithJackson(ObjectMapper mapper, String jsonString) {
        StringReader reader = new StringReader(jsonString);
        Person person = null;
        try {
            person = mapper.readValue(reader, PersonImpl.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return person;
    }

    @Override
    public Person readWithGson(GsonBuilder builder, String jsonString) {
        Gson gson = builder.create();
        Person person = gson.fromJson(jsonString, PersonImpl.class);
        return person;
    }
}

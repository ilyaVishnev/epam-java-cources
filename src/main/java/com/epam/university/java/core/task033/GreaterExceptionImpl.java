package com.epam.university.java.core.task033;

public class GreaterExceptionImpl extends RuntimeException
        implements GreaterException, BaseException {

    public GreaterExceptionImpl(String message) {
        super(message);
    }
}

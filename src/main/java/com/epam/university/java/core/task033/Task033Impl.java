package com.epam.university.java.core.task033;

public class Task033Impl implements Task033 {

    /**
     * Throws different exceptions.
     *
     * @param first  first value
     * @param second second value
     */
    @Override
    public void doSomething(int first, int second) {
        if (first == second) {
            int third = first / 0;
        }
        try {
            throw new BaseExceptionImpl();
        } catch (BaseExceptionImpl ex) {
            if (first > second) {
                GreaterExceptionImpl greaterException = new GreaterExceptionImpl("First > Second");
                greaterException.initCause(ex);
                throw greaterException;
            } else {
                LessExceptionImpl lessException = new LessExceptionImpl("Second > First");
                lessException.initCause(ex);
                throw lessException;
            }
        }
    }
}

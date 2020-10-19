package com.epam.university.java.project.core.state.machine.domain;

/**
 * rules for changing statuses.
 *
 * @param <T>  bookstatus
 * @param <E> bookevent
 */
public class StateMachineStateImpl<T, E> implements StateMachineState<T, E> {

    private T from;
    private T to;
    private E on;
    private String methodToCall;

    @Override
    public T getFrom() {
        return from;
    }

    @Override
    public void setFrom(T state) {
        this.from = state;
    }

    @Override
    public T getTo() {
        return this.to;
    }

    @Override
    public void setTo(T state) {
        this.to = state;
    }

    @Override
    public E getOn() {
        return this.on;
    }

    @Override
    public void setOn(E event) {
        this.on = event;
    }

    @Override
    public String getMethodToCall() {
        return methodToCall;
    }

    @Override
    public void setMethodToCall(String method) {
        this.methodToCall = method;
    }
}

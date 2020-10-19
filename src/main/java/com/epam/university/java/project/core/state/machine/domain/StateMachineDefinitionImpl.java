package com.epam.university.java.project.core.state.machine.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class StateMachineDefinitionImpl<S, E> implements
        StateMachineDefinition<S, E> {

    private E startEvent;
    private S startState;
    private List<StateMachineState<S, E>> states = new ArrayList<>();
    private Class<? extends StateMachineEventHandler> handlerClass;

    @Override
    public E getStartEvent() {
        return this.startEvent;
    }

    @Override
    public S getStartState() {
        return this.startState;
    }

    @Override
    public void setStartEvent(E event) {
        this.startEvent = event;
    }

    @Override
    public void setStartState(S state) {
        this.startState = state;
    }

    @Override
    public Collection<StateMachineState<S, E>> getStates() {
        return this.states;
    }

    @Override
    public void addState(StateMachineState<S, E> state) {
        this.states.add(state);
    }

    @Override
    public Class<? extends StateMachineEventHandler> getHandlerClass() {
        return this.handlerClass;
    }

    public void setStates(List<StateMachineState<S, E>> states) {
        this.states = states;
    }

    @Override
    public void setHandlerClass(Class<? extends StateMachineEventHandler> handlerClass) {
        this.handlerClass = handlerClass;
    }
}

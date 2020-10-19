package com.epam.university.java.project.core.state.machine.domain;

public class StatefulEntityImpl<S, E> implements StatefulEntity<S, E> {

    private S state;
    private StateMachineDefinition<S, E> stateMachineDefinition;

    @Override
    public S getState() {
        return this.state;
    }

    @Override
    public void setState(S state) {
        this.state = state;
    }

    @Override
    public StateMachineDefinition<S, E> getStateMachineDefinition() {
        return this.stateMachineDefinition;
    }

    @Override
    public void setStateMachineDefinition(StateMachineDefinition<S, E> definition) {
        this.stateMachineDefinition = definition;
    }
}

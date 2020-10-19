package com.epam.university.java.project.core.state.machine.domain;


public class StateMachineDefinitionBuilderImpl<S, E>
        implements StateMachineDefinitionBuilder<S, E> {

    private StateMachineDefinition<S, E> stateMachineDefinition =
            new StateMachineDefinitionImpl<>();
    private StateMachineDefinitionBuilder<S, E> stateMachineDefinitionBuilder =
            new StateMachineDefinitionBuilderImpl<>();

    @Override
    public StateMachineDefinition<S, E> build() {
        return this.stateMachineDefinition;
    }

    @Override
    @SuppressWarnings("unchecked")
    public StateMachineDefinitionBuilder<S, E> addState(S from, S to,
                                                        E on, String method) {
        StateMachineStateImpl state = new StateMachineStateImpl();
        state.setFrom(from);
        state.setTo(to);
        state.setOn(on);
        state.setMethodToCall(method);
        stateMachineDefinition.addState(state);
        return stateMachineDefinitionBuilder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public StateMachineDefinitionBuilder<S, E> addState(S from,
                                                        S to, E on) {
        StateMachineStateImpl state = new StateMachineStateImpl();
        state.setFrom(from);
        state.setTo(to);
        state.setOn(on);
        stateMachineDefinition.getStates().add(state);
        return stateMachineDefinitionBuilder;
    }
}

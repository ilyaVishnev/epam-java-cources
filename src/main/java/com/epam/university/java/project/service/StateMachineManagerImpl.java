package com.epam.university.java.project.service;


import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineState;
import com.epam.university.java.project.core.state.machine.domain.StatefulEntity;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StateMachineManagerImpl implements StateMachineManager {

    public StateMachineManagerImpl() {
    }

    @Override
    public StateMachineDefinition<?, ?> loadDefinition(Resource resource) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        StateMachineHandler handler = new StateMachineHandler();
        try {
            SAXParser parser = factory.newSAXParser();
            File file = resource.getFile();
            parser.parse(resource.getFile(), handler);
        } catch (Exception ex) {
            System.out.println();
        }
        StateMachineDefinition stateMachineDefinition = handler.getStateMachineDefinition();
        return stateMachineDefinition;
    }

    @Override
    public <S, E> StatefulEntity<S, E> initStateMachine(StatefulEntity<S, E> entity,
                                                        StateMachineDefinition<S, E> definition) {
        entity.setStateMachineDefinition(definition);
        entity.setState(definition.getStartState());
        return entity;
    }

    @Override
    public <S, E> StatefulEntity<S, E> handleEvent(StatefulEntity<S, E> entity, E event) {
        StateMachineDefinition<S, E> stateMachineDefinition = entity.getStateMachineDefinition();
        S status = entity.getState();
        List<StateMachineState<S, E>> states = new ArrayList<>(stateMachineDefinition.getStates());
        for (StateMachineState<S, E> state : states) {
            if (state.getFrom().equals(status)
                    && state.getOn().equals(event)) {
                entity.setState(state.getTo());
            }
        }
        return entity;
    }
}

package com.epam.university.java.project.service;

import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.domain.StateMachineState;
import com.epam.university.java.project.core.state.machine.domain.StateMachineEventHandler;
import com.epam.university.java.project.core.state.machine.domain.StateMachineStateImpl;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class StateMachineHandler extends DefaultHandler {

    private StateMachineDefinitionImpl<BookStatus, BookEvent> stateMachineDefinition =
            new StateMachineDefinitionImpl<>();
    private List<StateMachineState<BookStatus, BookEvent>> states = new ArrayList<>();

    @Override
    @SuppressWarnings("unchecked")
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        try {
            if (qName.equals("definition")) {
                stateMachineDefinition.setStartEvent(BookEvent
                        .valueOf(attributes.getValue("startEvent")));
                stateMachineDefinition.setStartState(BookStatus
                        .valueOf(attributes.getValue("startState")));
                Class<?> classHolder = ClassLoader.getSystemClassLoader()
                        .loadClass(attributes.getValue("handler"));
                stateMachineDefinition
                        .setHandlerClass((Class<? extends StateMachineEventHandler>) classHolder);
            }
            if (qName.equals("transition")) {
                StateMachineState<BookStatus, BookEvent> state = new StateMachineStateImpl<>();
                state.setFrom(BookStatus.valueOf(attributes.getValue("from")));
                state.setTo(BookStatus.valueOf(attributes.getValue("to")));
                state.setOn(BookEvent.valueOf(attributes.getValue("on")));
                state.setMethodToCall(attributes.getValue("call"));
                states.add(state);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("definition")) {
            stateMachineDefinition.setStates(states);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    public StateMachineDefinition<?, ?> getStateMachineDefinition() {
        return stateMachineDefinition;
    }
}

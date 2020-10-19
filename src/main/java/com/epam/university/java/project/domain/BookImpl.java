package com.epam.university.java.project.domain;


import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookImpl implements Book {

    private int id;
    private String title;
    private List<String> authors = new ArrayList<>();
    private String serialNumber;
    private LocalDate returnDate;
    private BookStatus state;
    private StateMachineDefinition<BookStatus, BookEvent> stateMachineDefinition
            = new StateMachineDefinitionImpl<BookStatus, BookEvent>();

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Collection<String> getAuthors() {
        return this.authors;
    }

    @Override
    public void setAuthors(Collection<String> authors) {
        this.authors = new ArrayList<>(authors);
    }

    @Override
    public String getSerialNumber() {
        return this.serialNumber;
    }

    @Override
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    @Override
    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    @Override
    public void setReturnDate(LocalDate date) {
        this.returnDate = date;
    }

    @Override
    public BookStatus getState() {
        return this.state;
    }

    @Override
    public void setState(BookStatus bookStatus) {
        this.state = bookStatus;
    }

    @Override
    public StateMachineDefinition<BookStatus, BookEvent> getStateMachineDefinition() {
        return this.stateMachineDefinition;
    }

    @Override
    public void setStateMachineDefinition(StateMachineDefinition<BookStatus,
            BookEvent> definition) {
        this.stateMachineDefinition = definition;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Book book = (Book) obj;
        if (this.getId() == book.getId()) {
            return true;
        }
        return false;
    }
}

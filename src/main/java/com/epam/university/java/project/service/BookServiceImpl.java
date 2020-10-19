package com.epam.university.java.project.service;


import com.epam.university.java.project.core.cdi.impl.io.XmlResource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookImpl;
import com.epam.university.java.project.domain.BookStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoXmlImpl();
    private StateMachineManager stateMachineManager = new StateMachineManagerImpl();

    @Override
    @SuppressWarnings("unchecked")
    public Book createBook() {
        final String contextPath = getClass()
                .getResource("/project/DefaultBookStateMachineDefinition.xml")
                .getFile();
        StateMachineDefinition<BookStatus, BookEvent> stateMachineDefinition =
                (StateMachineDefinition<BookStatus, BookEvent>) stateMachineManager
                        .loadDefinition(new XmlResource(contextPath));
        Book book = bookDao.createBook();
        stateMachineManager.initStateMachine(book, stateMachineDefinition);
        stateMachineManager.handleEvent(book, BookEvent.CREATE);
        return book;
    }

    @Override
    public Book getBook(int id) {
        List<Book> books = new ArrayList<>(bookDao.getBooks());
        Optional<Book> book = books.stream().filter(b -> b.getId() == id)
                .findFirst();
        return book.isPresent() ? book.get() : null;
    }

    @Override
    public Collection<Book> getBooks() {
        return this.bookDao.getBooks();
    }

    @Override
    public void remove(Book book) {
        this.bookDao.remove(book);
    }

    @Override
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book accept(Book book, String number) {
        book.setSerialNumber(number);
        stateMachineManager.handleEvent(book, BookEvent.ACCEPT);
        return this.bookDao.save(book);
    }

    @Override
    public Book issue(Book book, LocalDate returnDate) {
        book.setReturnDate(returnDate);
        stateMachineManager.handleEvent(book, BookEvent.ISSUE);
        return bookDao.save(book);
    }

    @Override
    public Book returnFromIssue(Book book) {
        stateMachineManager.handleEvent(book, BookEvent.RETURN);
        return bookDao.save(book);
    }
}

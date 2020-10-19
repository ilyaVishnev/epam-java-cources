package com.epam.university.java.project.service;

import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookImpl;
import com.epam.university.java.project.domain.BookStatus;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.Collection;


public class BookDaoXmlImpl implements BookDao {

    private Set<Book> books = new HashSet<>();
    private int index = 1;

    @Override
    public Book createBook() {
        Book book = new BookImpl();
        return book;
    }

    @Override
    public Book getBook(int id) {
        Optional<Book> book = books.stream().filter(b -> b.getId() == id).findFirst();
        return book.isPresent() ? book.get() : null;
    }

    @Override
    public Collection<Book> getBooks() {
        return this.books;
    }

    @Override
    public void remove(Book book) {
        books.remove(book);
    }

    @Override
    public Book save(Book book) {
        book.setId(index++);
        books.add(book);
        return book;
    }
}

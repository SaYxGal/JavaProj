package com.labwork01.app.book.controller;

import com.labwork01.app.book.model.Book;

public class BookDto {
    private final long id;
    private final String name;
    private final String description;
    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

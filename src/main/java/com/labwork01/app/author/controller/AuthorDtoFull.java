package com.labwork01.app.author.controller;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.book.controller.BookDtoWithoutAuthor;

import java.util.List;

public class AuthorDtoFull {
    private final long id;
    private final String name;
    private final String surname;
    private final String patronymic;
    private final List<BookDtoWithoutAuthor> books;
    public AuthorDtoFull(Author author){
        id = author.getId();
        name = author.getName();
        surname = author.getSurname();
        patronymic = author.getPatronymic();
        books = author.getBooks().stream().map(BookDtoWithoutAuthor::new).toList();
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public List<BookDtoWithoutAuthor> getBooks() {
        return books;
    }
}

package com.labwork01.app.author.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.labwork01.app.author.model.Author;
import com.labwork01.app.book.model.Book;

import java.util.List;

public class AuthorDto {
    private final long id;
    private final String name;
    private final String surname;
    private final String patronymic;
    @JsonIgnoreProperties("author")
    private final List<Book> books;
    public AuthorDto(Author author){
        id = author.getId();
        name = author.getName();
        surname = author.getSurname();
        patronymic = author.getPatronymic();
        books = author.getBooks();
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

    public List<Book> getBooks() {
        return books;
    }
}

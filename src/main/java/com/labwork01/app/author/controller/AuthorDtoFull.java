package com.labwork01.app.author.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labwork01.app.author.model.Author;
import com.labwork01.app.book.controller.BookDtoWithoutAuthor;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class AuthorDtoFull {
    private final long id;
    @NotBlank(message = "Name can't be null or empty")
    private final String name;
    @NotBlank(message = "Surname can't be null or empty")
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

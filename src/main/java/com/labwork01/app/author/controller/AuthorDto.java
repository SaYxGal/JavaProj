package com.labwork01.app.author.controller;

import com.labwork01.app.author.model.Author;

public class AuthorDto {
    private final long id;
    private final String name;
    private final String surname;
    private final String patronymic;
    public AuthorDto(Author author){
        id = author.getId();
        name = author.getName();
        surname = author.getSurname();
        patronymic = author.getPatronymic();
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
}

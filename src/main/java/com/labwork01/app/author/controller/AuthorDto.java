package com.labwork01.app.author.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labwork01.app.author.model.Author;
import jakarta.validation.constraints.NotBlank;

public class AuthorDto {
    private long id;
    @NotBlank(message = "Name can't be null or empty")
    private String name;
    @NotBlank(message = "Surname can't be null or empty")
    private String surname;
    private String patronymic;
    public AuthorDto(){

    }
    public AuthorDto(Author author){
        id = author.getId();
        name = author.getName();
        surname = author.getSurname();
        patronymic = author.getPatronymic();
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
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

}

package com.labwork01.app.genre.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labwork01.app.genre.model.Genre;
import jakarta.validation.constraints.NotBlank;

public class GenreDto {
    private long id;
    @NotBlank(message = "Name can't be null or empty")
    private String name;
    public GenreDto() {
    }
    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}

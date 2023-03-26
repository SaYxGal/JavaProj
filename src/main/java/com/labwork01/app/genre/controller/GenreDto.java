package com.labwork01.app.genre.controller;

import com.labwork01.app.genre.model.Genre;

public class GenreDto {
    private final long id;
    private final String name;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

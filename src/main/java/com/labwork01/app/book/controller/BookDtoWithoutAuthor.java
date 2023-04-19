package com.labwork01.app.book.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.controller.GenreDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookDtoWithoutAuthor {
    private final long id;
    @NotBlank(message = "Name can't be null or empty")
    private final String name;
    @NotBlank(message = "Description can't be null or empty")
    private final String description;
    @NotNull(message = "Genres can't be null")
    private final List<GenreDto> genres;
    public BookDtoWithoutAuthor(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.genres = book.getGenres().stream().map(GenreDto::new).toList();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<GenreDto> getGenres(){
        return genres;
    }
}

package com.labwork01.app.book.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookDtoForTL {
    private long id;
    @NotBlank(message = "Name can't be null or empty")
    private String name;
    @NotBlank(message = "Description can't be null or empty")
    private String description;
    @NotNull(message = "AuthorId can't be null")
    private long authorId;
    @NotNull(message = "Genres can't be null")
    private List<Long> genresId;
    public BookDtoForTL(){

    }
    public BookDtoForTL(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.authorId = book.getAuthor().getId();
        this.genresId = book.getGenres().stream().map(Genre::getId).toList();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public long getAuthorId() {
        return authorId;
    }
    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
    public List<Long> getGenresId() {
        return genresId;
    }
    public void setGenresId(List<Long> genresId) {
        this.genresId = genresId;
    }
}

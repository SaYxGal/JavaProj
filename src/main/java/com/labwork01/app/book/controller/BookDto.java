package com.labwork01.app.book.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.labwork01.app.author.controller.AuthorDto;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.controller.GenreDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookDto {
    private long id;
    @NotBlank(message = "Name can't be null or empty")
    private String name;
    @NotBlank(message = "Description can't be null or empty")
    private String description;
    @NotNull(message = "Author can't be null")
    private AuthorDto author;
    @NotNull(message = "Genres can't be null")
    private List<GenreDto> genres;
    public BookDto(){

    }
    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.author = new AuthorDto(book.getAuthor());
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
    public AuthorDto getAuthor() {
        return author;
    }
    public List<GenreDto> getGenres(){
        return genres;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }
}

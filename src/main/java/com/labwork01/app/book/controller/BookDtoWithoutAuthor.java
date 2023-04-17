package com.labwork01.app.book.controller;

import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.controller.GenreDto;

import java.util.List;

public class BookDtoWithoutAuthor {
    private final long id;
    private final String name;
    private final String description;
    private final List<GenreDto> genres;
    public BookDtoWithoutAuthor(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.genres = book.getGenres().stream().map(GenreDto::new).toList();
    }
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

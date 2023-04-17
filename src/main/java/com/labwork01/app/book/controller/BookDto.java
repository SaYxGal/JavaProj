package com.labwork01.app.book.controller;

import com.labwork01.app.author.controller.AuthorDto;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.controller.GenreDto;

import java.util.List;

public class BookDto {
    private final long id;
    private final String name;
    private final String description;
    private final AuthorDto author;
    private final List<GenreDto> genres;
    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.author = new AuthorDto(book.getAuthor());
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
    public AuthorDto getAuthor() {
        return author;
    }
    public List<GenreDto> getGenres(){
        return genres;
    }
}

package com.labwork01.app.book.controller;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.model.Genre;

import java.util.List;

public class BookDto {
    private final long id;
    private final String name;
    private final String description;
    private final Author author;
    private final List<Genre> genres;
    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.author = book.getAuthor();
        this.genres = book.getGenres();
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
    public Author getAuthor() {
        return author;
    }
    public List<Genre> getGenres(){
        return genres;
    }
}

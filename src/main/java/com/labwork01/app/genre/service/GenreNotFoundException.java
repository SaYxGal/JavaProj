package com.labwork01.app.genre.service;
public class GenreNotFoundException extends RuntimeException{
    public GenreNotFoundException(Long id) {
        super(String.format("Genre with id [%s] is not found", id));
    }
}

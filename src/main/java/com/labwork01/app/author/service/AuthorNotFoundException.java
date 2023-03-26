package com.labwork01.app.author.service;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super(String.format("Author with id [%s] is not found", id));
    }
}

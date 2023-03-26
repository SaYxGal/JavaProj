package com.labwork01.app.book.service;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super(String.format("Book with id [%s] is not found", id));
    }
}

package com.labwork01.app.book.service;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.model.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Book addBook(String name, String description, Author author, List<Genre> genres) {
        if (!StringUtils.hasText(name) || !StringUtils.hasText(description) || author == null || genres == null) {
            throw new IllegalArgumentException("Book fields is null or empty");
        }
        final Book book = new Book(name, description);
        book.setAuthor(author);
        for (Genre genre: genres) {
            book.addGenre(genre);
        }
        em.persist(book);
        return book;
    }

    @Transactional(readOnly = true)
    public Book findBook(Long id) {
        final Book book = em.find(Book.class, id);
        if (book == null) {
            throw new EntityNotFoundException(String.format("Book with id [%s] is not found", id));
        }
        return book;
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return em.createQuery("select s from Book s", Book.class)
                .getResultList();
    }

    @Transactional
    public Book updateBook(Long id, String name, String description, Author author) {
        if (!StringUtils.hasText(name) || !StringUtils.hasText(description) || author == null) {
            throw new IllegalArgumentException("Book fields is null or empty");
        }
        final Book currentBook = findBook(id);
        currentBook.setName(name);
        currentBook.setDescription(description);
        currentBook.setAuthor(author);
        return em.merge(currentBook);
    }
    @Transactional
    public Book addGenreToBook(Long id, Genre genre){
        if(id <= 0 || genre == null){
            throw new IllegalArgumentException("Book fields is null or empty");
        }
        final Book currentBook = findBook(id);
        currentBook.addGenre(genre);
        return em.merge(currentBook);
    }
    @Transactional
    public Book removeGenreFromBook(Long id, Genre genre){
        if(id <= 0 || genre == null){
            throw new IllegalArgumentException("Book fields is null or empty");
        }
        final Book currentBook = findBook(id);
        currentBook.removeGenre(genre);
        return em.merge(currentBook);
    }
    @Transactional
    public Book deleteBook(Long id) {
        final Book currentBook = findBook(id);
        em.remove(currentBook);
        return currentBook;
    }

    @Transactional
    public void deleteAllBooks() {
        em.createQuery("delete from Book").executeUpdate();
    }
}

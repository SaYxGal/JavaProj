package com.labwork01.app;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.author.service.AuthorService;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.book.service.BookNotFoundException;
import com.labwork01.app.book.service.BookService;
import com.labwork01.app.genre.model.Genre;
import com.labwork01.app.genre.service.GenreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class JpaBookTests {
    private static final Logger log = LoggerFactory.getLogger(JpaBookTests.class);
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;
    @Test
    void testCreateBooks(){
        bookService.deleteAllBooks();
        genreService.deleteAllGenres();
        authorService.deleteAllAuthors();
        final Genre genre1 = genreService.addGenre("Фэнтези");
        final Genre genre2 = genreService.addGenre("Комедия");
        final Author author = authorService.addAuthor("Иван", "Иванов", "Иванович");
        ArrayList<Genre> genres1 = new ArrayList<>();
        genres1.add(genre1);
        genres1.add(genre2);
        ArrayList<Genre> genres2 = new ArrayList<>();
        genres2.add(genre1);
        final Book book1 = bookService.addBook("Война и мир", "Описание 1", author,genres1);
        final Book book2 = bookService.addBook("Мастер и Маргарита", "Описание 2", author, genres2);
        log.info("testCreateBooks[0] " + book1.toString());
        log.info("testCreateBooks[1] " + book2.toString());
        Assertions.assertNotNull(book1.getId());
        Assertions.assertNotNull(book2.getId());
    }
    @Test
    void testBookRead() {
        bookService.deleteAllBooks();
        genreService.deleteAllGenres();
        authorService.deleteAllAuthors();
        final Genre genre1 = genreService.addGenre("Сатира");
        final Genre genre2 = genreService.addGenre("Приключения");
        final Author author = authorService.addAuthor("Антон", "Чехов", "Павлович");
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);
        final Book book = bookService.addBook("Хлеб насущный", "Описание 3", author,genres);
        log.info("testBookRead[0]: " + book.toString());
        final Book findBook = bookService.findBook(book.getId());
        log.info("testBookRead[1]: " + findBook.toString());
        Assertions.assertEquals(book, findBook);
        bookService.deleteAllBooks();
        genreService.deleteAllGenres();
        authorService.deleteAllAuthors();
    }
    @Test
    void testChangeAuthor(){
        bookService.deleteAllBooks();
        genreService.deleteAllGenres();
        authorService.deleteAllAuthors();
        final Genre genre1 = genreService.addGenre("Драма");
        final Genre genre2 = genreService.addGenre("Триллер");
        Author author1 = authorService.addAuthor("Петр", "Петров", "Петрович");
        Author author2 = authorService.addAuthor("Евгений", "Попов", "Евгеньевич");
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);
        Book book = bookService.addBook("Что делать?", "Описание 4", author1,genres);
        log.info("testChangeAuthor[0] Book: " + book.toString());
        log.info("testChangeAuthor[0] Author: " + author1.toString() + " " + author1.getBooks());
        Assertions.assertEquals(book.getAuthor(), author1);
        bookService.updateBook(book.getId(),"Что делать?", "Описание 4", author2);
        book = bookService.findBook(book.getId());
        author1 = authorService.findAuthor(author1.getId());
        author2 = authorService.findAuthor(author2.getId());
        log.info("testChangeAuthor[1] Book: " + book.toString());
        log.info("testChangeAuthor[1] Author: " + author2.toString() + " " + author2.getBooks());
        log.info("testChangeAuthor[1] PreviousAuthor: " + author1.toString() + " " + author1.getBooks());
        Assertions.assertEquals(book.getAuthor(), author2);
    }
    @Test
    void testRemoveAndAddGenres(){
        bookService.deleteAllBooks();
        genreService.deleteAllGenres();
        authorService.deleteAllAuthors();
        Genre genre1 = genreService.addGenre("Драма");
        Genre genre2 = genreService.addGenre("Триллер");
        Genre genre3 = genreService.addGenre("Исторический");
        Author author = authorService.addAuthor("Петр", "Петров", "Петрович");
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);
        Book book = bookService.addBook("Многожанровость", "Описание 5", author,genres);
        log.info("testRemoveAndAddGenres[0]: " + book.toString());
        bookService.removeGenreFromBook(book.getId(), genre1);
        book = bookService.findBook(book.getId());
        genre1 = genreService.findGenre(genre1.getId());
        log.info("testRemoveAndAddGenres[1]: " + book.toString());
        Assertions.assertEquals(book.getGenres().size(), 1);
        Assertions.assertEquals(genre1.getBooks().size(), 0);
        bookService.addGenreToBook(book.getId(), genre3);
        book = bookService.findBook(book.getId());
        genre3 = genreService.findGenre(genre3.getId());
        log.info("testRemoveAndAddGenres[2]: " + book.toString());
        Assertions.assertEquals(book.getGenres().size(), 2);
        Assertions.assertEquals(genre3.getBooks().size(), 1);
    }
    @Test
    void testBookReadNotFound() {
        bookService.deleteAllBooks();
        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.findBook(-1L));
    }
    @Test
    void testBookReadAllEmpty() {
        bookService.deleteAllBooks();
        final List<Book> books = bookService.findAllBooks();
        log.info("testBookReadAllEmpty: " + books.toString());
        Assertions.assertEquals(books.size(), 0);
    }
}

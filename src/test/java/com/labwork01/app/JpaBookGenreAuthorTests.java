package com.labwork01.app;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.author.service.AuthorService;
import com.labwork01.app.book.model.Book;
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

@SpringBootTest
public class JpaBookGenreAuthorTests {
    private static final Logger log = LoggerFactory.getLogger(JpaBookGenreAuthorTests.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;

    @Test
    void testGenresCreate(){
        genreService.deleteAllGenres();
        final Genre genre1 = genreService.addGenre("Фэнтези");
        final Genre genre2 = genreService.addGenre("Комедия");
        log.info(genre1.toString());
        log.info(genre2.toString());
        Assertions.assertNotNull(genre1.getId());
        Assertions.assertNotNull(genre2.getId());
    }
    @Test
    void testAuthorCreate(){
        authorService.deleteAllAuthors();
        final Author author = authorService.addAuthor("Иван", "Иванов", "Иванович");
        log.info(author.toString());
        Assertions.assertNotNull(author.getId());
    }
    @Test
    void testBooksCreate() {
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
        log.info(book1.toString());
        log.info(book2.toString());
        Assertions.assertNotNull(book1.getId());
        Assertions.assertNotNull(book2.getId());
    }

    /*@Test
    void testStudentRead() {
        genreService.deleteAllGenres();
        authorService.deleteAllAuthors();
        bookService.deleteAllBooks();
        final Book book = bookService.addBook("Иван", "Иванов");
        log.info(book.toString());
        final Book findBook = bookService.findStudent(book.getId());
        log.info(findBook.toString());
        Assertions.assertEquals(book, findBook);
    }

    @Test
    void testStudentReadNotFound() {
        bookService.deleteAllStudents();
        Assertions.assertThrows(EntityNotFoundException.class, () -> bookService.findStudent(-1L));
    }

    @Test
    void testStudentReadAll() {
        bookService.deleteAllStudents();
        bookService.addStudent("Иван", "Иванов");
        bookService.addStudent("Петр", "Петров");
        final List<Book> books = bookService.findAllStudents();
        log.info(books.toString());
        Assertions.assertEquals(books.size(), 2);
    }

    @Test
    void testStudentReadAllEmpty() {
        bookService.deleteAllStudents();
        final List<Book> books = bookService.findAllStudents();
        log.info(books.toString());
        Assertions.assertEquals(books.size(), 0);
    }*/
}

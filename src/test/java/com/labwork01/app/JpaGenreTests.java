package com.labwork01.app;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.author.service.AuthorService;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.book.service.BookService;
import com.labwork01.app.genre.model.Genre;
import com.labwork01.app.genre.service.GenreNotFoundException;
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
public class JpaGenreTests {
    private static final Logger log = LoggerFactory.getLogger(JpaGenreTests.class);
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Test
    void testGenresCreate(){
        genreService.deleteAllGenres();
        final Genre genre1 = genreService.addGenre("Фэнтези");
        final Genre genre2 = genreService.addGenre("Комедия");
        log.info("testCreateGenre " + genre1.toString());
        log.info("testCreateGenre " + genre2.toString());
        Assertions.assertNotNull(genre1.getId());
        Assertions.assertNotNull(genre2.getId());
    }
    @Test
    void testGenreRead() {
        genreService.deleteAllGenres();
        final Genre genre = genreService.addGenre("Романтика");
        log.info("testGenreRead[0]: " + genre.toString());
        final Genre findGenre = genreService.findGenre(genre.getId());
        log.info("testGenreRead[1]: " + findGenre.toString());
        Assertions.assertEquals(genre, findGenre);
        genreService.deleteAllGenres();
    }
    @Test
    void testGenreReadNotFound() {
        genreService.deleteAllGenres();
        Assertions.assertThrows(GenreNotFoundException.class, () -> genreService.findGenre(-1L));
    }

    @Test
    void testGenreReadAll() {
        genreService.deleteAllGenres();
        genreService.addGenre("Приключения");
        genreService.addGenre("Триллер");
        final List<Genre> genres = genreService.findAllGenres();
        log.info("testGenreReadAll: " + genres.toString());
        Assertions.assertEquals(genres.size(), 2);
        genreService.deleteAllGenres();
    }
    @Test
    void testFindBooksWithGenre(){
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
        final List<Book> books1 = genreService.findAllBooksWithGenre(genre1);
        log.info("testFindBooksWithGenre[0]: " + books1.toString());
        Assertions.assertTrue(books1.contains(book1) && books1.contains(book2));
        final List<Book> books2 = genreService.findAllBooksWithGenre(genre2);
        log.info("testFindBooksWithGenre[1]: " + books2.toString());
        Assertions.assertTrue(books2.contains(book1));
        bookService.deleteAllBooks();
        genreService.deleteAllGenres();
        authorService.deleteAllAuthors();
    }
    @Test
    void testGenreReadAllEmpty() {
        genreService.deleteAllGenres();
        final List<Genre> genres = genreService.findAllGenres();
        log.info("testGenreReadAllEmpty: " + genres.toString());
        Assertions.assertEquals(genres.size(), 0);
    }
}

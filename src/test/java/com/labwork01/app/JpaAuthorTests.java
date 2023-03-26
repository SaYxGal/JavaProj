package com.labwork01.app;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.author.service.AuthorNotFoundException;
import com.labwork01.app.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JpaAuthorTests {
    private static final Logger log = LoggerFactory.getLogger(JpaAuthorTests.class);
    @Autowired
    private AuthorService authorService;
    @Test
    void testAuthorsCreate(){
        authorService.deleteAllAuthors();
        final Author author1 = authorService.addAuthor("Данил", "Малин", "Валерьевич");
        final Author author2 = authorService.addAuthor("Иван", "Иванов", "Иванович");
        log.info("testAuthorsCreate[0] " + author1.toString());
        log.info("testAuthorsCreate[1] " + author2.toString());
        Assertions.assertNotNull(author1.getId());
        Assertions.assertNotNull(author2.getId());
    }
    @Test
    void testAuthorRead() {
        authorService.deleteAllAuthors();
        final Author author = authorService.addAuthor("Антон", "Чехов", "Павлович");
        log.info("testAuthorRead[0]: " + author.toString());
        final Author findAuthor = authorService.findAuthor(author.getId());
        log.info("testAuthorRead[1]: " + findAuthor.toString());
        Assertions.assertEquals(author, findAuthor);
        authorService.deleteAllAuthors();
    }
    @Test
    void testAuthorReadNotFound() {
        authorService.deleteAllAuthors();
        Assertions.assertThrows(AuthorNotFoundException.class, () -> authorService.findAuthor(-1L));
    }

    @Test
    void testAuthorReadAll() {
        authorService.deleteAllAuthors();
        authorService.addAuthor("Лев", "Толстой", "Николаевич");
        authorService.addAuthor("Федор", "Достоевский", "Михайлович");
        final List<Author> authors = authorService.findAllAuthors();
        log.info("testAuthorReadAll: " + authors.toString());
        Assertions.assertEquals(authors.size(), 2);
        authorService.deleteAllAuthors();
    }
    @Test
    void testAuthorReadAllEmpty() {
        authorService.deleteAllAuthors();
        final List<Author> authors = authorService.findAllAuthors();
        log.info("testAuthorReadAllEmpty: " + authors.toString());
        Assertions.assertEquals(authors.size(), 0);
    }
}

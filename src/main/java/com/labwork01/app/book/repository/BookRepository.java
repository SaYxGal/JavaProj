package com.labwork01.app.book.repository;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE :genre MEMBER OF b.genres")
    List<Book> getBooksByGenre(@Param("genre")Genre genre);
    @Query("SELECT b FROM Book b WHERE b.author = :author")
    List<Book> getBooksByAuthor(@Param("author") Author author);
    @Query("SELECT b FROM Book b WHERE b.name LIKE :name")
    List<Book> getBooksByName(@Param("name") String name);
}

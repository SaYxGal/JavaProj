package com.labwork01.app.genre.repository;

import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("SELECT b FROM Book b WHERE ?1 MEMBER OF b.genres")
    List<Book>getBooksWithGenre(Genre genre);
}

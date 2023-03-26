package com.labwork01.app.book.repository;

import com.labwork01.app.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book, Long> {
}

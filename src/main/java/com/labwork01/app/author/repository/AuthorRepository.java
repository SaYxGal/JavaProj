package com.labwork01.app.author.repository;

import com.labwork01.app.author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

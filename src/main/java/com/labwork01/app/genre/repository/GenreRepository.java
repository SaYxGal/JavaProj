package com.labwork01.app.genre.repository;

import com.labwork01.app.genre.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GenreRepository extends JpaRepository<Genre, Long> {
}

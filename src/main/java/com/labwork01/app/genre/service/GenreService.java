package com.labwork01.app.genre.service;

import com.labwork01.app.genre.model.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class GenreService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Genre addGenre(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Genre name is null or empty");
        }
        final Genre Genre = new Genre(name);
        em.persist(Genre);
        return Genre;
    }

    @Transactional(readOnly = true)
    public Genre findGenre(Long id) {
        final Genre Genre = em.find(Genre.class, id);
        if (Genre == null) {
            throw new EntityNotFoundException(String.format("Genre with id [%s] is not found", id));
        }
        return Genre;
    }

    @Transactional(readOnly = true)
    public List<Genre> findAllGenres() {
        return em.createQuery("select s from Genre s", Genre.class)
                .getResultList();
    }

    @Transactional
    public Genre updateGenre(Long id, String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Genre name is null or empty");
        }
        final Genre currentGenre = findGenre(id);
        currentGenre.setName(name);
        return em.merge(currentGenre);
    }

    @Transactional
    public Genre deleteGenre(Long id) {
        final Genre currentGenre = findGenre(id);
        em.remove(currentGenre);
        return currentGenre;
    }

    @Transactional
    public void deleteAllGenres() {
        em.createQuery("delete from Genre").executeUpdate();
    }
}

package com.labwork01.app.author.service;

import com.labwork01.app.author.model.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class AuthorService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Author addAuthor(String name, String surname, String patronymic) {
        if (!StringUtils.hasText(name) || !StringUtils.hasText(surname) || !StringUtils.hasText(patronymic)) {
            throw new IllegalArgumentException("Author fields is null or empty");
        }
        final Author Author = new Author(name,surname,patronymic);
        em.persist(Author);
        return Author;
    }

    @Transactional(readOnly = true)
    public Author findAuthor(Long id) {
        final Author Author = em.find(Author.class, id);
        if (Author == null) {
            throw new EntityNotFoundException(String.format("Author with id [%s] is not found", id));
        }
        return Author;
    }

    @Transactional(readOnly = true)
    public List<Author> findAllAuthors() {
        return em.createQuery("select s from Author s", Author.class)
                .getResultList();
    }

    @Transactional
    public Author updateAuthor(Long id, String name, String surname, String patronymic) {
        if (!StringUtils.hasText(name) || !StringUtils.hasText(surname) || !StringUtils.hasText(patronymic)) {
            throw new IllegalArgumentException("Author fields is null or empty");
        }
        final Author currentAuthor = findAuthor(id);
        currentAuthor.setName(name);
        currentAuthor.setSurname(surname);
        currentAuthor.setPatronymic(patronymic);
        return em.merge(currentAuthor);
    }

    @Transactional
    public Author deleteAuthor(Long id) {
        final Author currentAuthor = findAuthor(id);
        em.remove(currentAuthor);
        return currentAuthor;
    }

    @Transactional
    public void deleteAllAuthors() {
        em.createQuery("delete from Author").executeUpdate();
    }
}

package com.labwork01.app.author.service;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.author.repository.AuthorRepository;
import com.labwork01.app.util.validation.ValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ValidatorUtil validatorUtil;
    public AuthorService(AuthorRepository authorRepository, ValidatorUtil validatorUtil){
        this.authorRepository = authorRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Author addAuthor(String name, String surname, String patronymic) {
        final Author author = new Author(name,surname,patronymic);
        validatorUtil.validate(author);
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public Author findAuthor(Long id) {
        final Optional<Author> author = authorRepository.findById(id);
        return author.orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Transactional
    public Author updateAuthor(Long id, String name, String surname, String patronymic) {
        final Author currentAuthor = findAuthor(id);
        currentAuthor.setName(name);
        currentAuthor.setSurname(surname);
        currentAuthor.setPatronymic(patronymic);
        validatorUtil.validate(currentAuthor);
        return authorRepository.save(currentAuthor);
    }

    @Transactional
    public Author deleteAuthor(Long id) {
        final Author currentAuthor = findAuthor(id);
        authorRepository.delete(currentAuthor);
        return currentAuthor;
    }

    @Transactional
    public void deleteAllAuthors() {
        authorRepository.deleteAll();
    }
}

package com.labwork01.app.genre.service;

import com.labwork01.app.book.model.Book;
import com.labwork01.app.genre.model.Genre;
import com.labwork01.app.genre.repository.GenreRepository;
import com.labwork01.app.user.model.User;
import com.labwork01.app.user.model.UserRole;
import com.labwork01.app.user.service.UserService;
import com.labwork01.app.util.validation.ValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    private final UserService userService;
    private final ValidatorUtil validatorUtil;
    public GenreService(GenreRepository genreRepository, UserService userService, ValidatorUtil validatorUtil){
        this.genreRepository = genreRepository;
        this.userService = userService;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Genre addGenre(String name, String userName) {
        final Genre genre = new Genre(name);
        User user = userService.findByLogin(userName);
        if(user.getRole() == UserRole.ADMIN){
            genre.setUser(user);
        }
        validatorUtil.validate(genre);
        return genreRepository.save(genre);
    }

    @Transactional(readOnly = true)
    public Genre findGenre(Long id) {
        final Optional<Genre> genre = genreRepository.findById(id);
        return genre.orElseThrow(() -> new GenreNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Transactional
    public Genre updateGenre(Long id, String name) {
        final Genre currentGenre = findGenre(id);
        currentGenre.setName(name);
        validatorUtil.validate(currentGenre);
        return genreRepository.save(currentGenre);
    }
    @Transactional(readOnly = true)
    public List<Book> findAllBooksWithGenre(Genre genre){
        if(genre == null){
            throw new IllegalArgumentException("Genre is null or empty");
        }
        return genreRepository.getBooksWithGenre(genre);
    }

    @Transactional
    public Genre deleteGenre(Long id) {
        final Genre currentGenre = findGenre(id);
        genreRepository.delete(currentGenre);
        return currentGenre;
    }

    @Transactional
    public void deleteAllGenres() {
        genreRepository.deleteAll();
    }
}

package com.labwork01.app.book.service;

import com.labwork01.app.author.model.Author;
import com.labwork01.app.author.service.AuthorService;
import com.labwork01.app.book.model.Book;
import com.labwork01.app.book.repository.BookRepository;
import com.labwork01.app.genre.model.Genre;
import com.labwork01.app.genre.service.GenreService;
import com.labwork01.app.user.model.User;
import com.labwork01.app.user.model.UserRole;
import com.labwork01.app.user.service.UserService;
import com.labwork01.app.util.validation.ValidatorUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final UserService userService;
    private final ValidatorUtil validatorUtil;
    public BookService(BookRepository bookRepository,GenreService genreService, AuthorService authorService, UserService userService, ValidatorUtil validatorUtil){
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.authorService = authorService;
        this.userService = userService;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Book addBook(String name, String description, Author author, List<Genre> genres) {
        final Book book = new Book(name, description);
        book.setAuthor(author);
        for (Genre genre: genres) {
            book.addGenre(genre);
        }
        Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser instanceof UserDetails){
            String username = ((UserDetails)currentUser).getUsername();
            User user = userService.findByLogin(username);
            if(user.getRole() == UserRole.ADMIN){
                book.setUser(user);
            }
        }
        validatorUtil.validate(book);
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book findBook(Long id) {
        final Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new BookNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooks(Long authorId, Long genreId, String name) {
        if(authorId != null){
            Author author = authorService.findAuthor(authorId);
            return bookRepository.getBooksByAuthor(author);
        }
        else if(genreId != null){
            Genre genre = genreService.findGenre(genreId);
            return bookRepository.getBooksByGenre(genre);
        }
        else if(name != null){
            return bookRepository.getBooksByName("%" + name + "%");
        }
        return bookRepository.findAll();
    }

    @Transactional
    public Book updateBook(Long id, String name, String description, Author author) {
        final Book currentBook = findBook(id);
        currentBook.setName(name);
        currentBook.setDescription(description);
        currentBook.setAuthor(author);
        validatorUtil.validate(currentBook);
        return bookRepository.save(currentBook);
    }
    @Transactional
    public Book updateBookFull(Long id, String name, String description, Author author, List<Genre> genres){
        final Book currentBook = findBook(id);
        currentBook.setName(name);
        currentBook.setDescription(description);
        currentBook.setAuthor(author);
        currentBook.setGenres(genres);
        return bookRepository.save(currentBook);
    }
    @Transactional
    public Book addGenreToBook(Long id, Genre genre){
        final Book currentBook = findBook(id);
        validatorUtil.validate(genre);
        currentBook.addGenre(genre);
        validatorUtil.validate(currentBook);
        return bookRepository.save(currentBook);
    }
    @Transactional
    public Book removeGenreFromBook(Long id, Genre genre){
        final Book currentBook = findBook(id);
        validatorUtil.validate(genre);
        currentBook.removeGenre(genre);
        validatorUtil.validate(currentBook);
        return bookRepository.save(currentBook);
    }
    @Transactional
    public Book deleteBook(Long id) {
        final Book currentBook = findBook(id);
        bookRepository.delete(currentBook);
        return currentBook;
    }

    @Transactional
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
}

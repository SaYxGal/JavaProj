package com.labwork01.app.book.controller;

import com.labwork01.app.author.service.AuthorService;
import com.labwork01.app.book.service.BookService;
import com.labwork01.app.genre.model.Genre;
import com.labwork01.app.genre.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService){
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }
    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return new BookDto(bookService.findBook(id));
    }

    @GetMapping
    public List<BookDto> getBooks(@RequestParam(value = "authorId", required = false) Long authorId,
                                  @RequestParam(value = "genreId",required = false) Long genreId,
                                  @RequestParam(value = "name", required = false) String name) {
        return bookService.findAllBooks(authorId, genreId, name).stream()
                .map(BookDto::new)
                .toList();
    }

    @PostMapping
    public BookDto createBook(@RequestParam("name") String name,
                              @RequestParam("desc") String description,
                              @RequestParam("authorId") Long authorId,
                              @RequestParam("login") String userName,
                              @RequestParam(value="genreId[]") Long[] genresId) {
        List<Genre> genres = new ArrayList<>();
        for (Long id: genresId) {
            genres.add(genreService.findGenre(id));
        }
        return new BookDto(bookService.addBook(name, description,
                authorService.findAuthor(authorId), genres, userName));
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id,
                              @RequestParam("name") String name,
                              @RequestParam("desc") String description,
                              @RequestParam("authorId") Long authorId) {
        return new BookDto(bookService.updateBook(id, name, description, authorService.findAuthor(authorId)));
    }
    @PostMapping("/{id}/genres/{genreId}")
    public BookDto addGenre(@PathVariable Long id,
                              @PathVariable Long genreId) {
        return new BookDto(bookService.addGenreToBook(id, genreService.findGenre(genreId)));
    }
    @PutMapping("/{id}/genres/{genreId}")
    public BookDto removeGenre(@PathVariable Long id,
                                 @PathVariable Long genreId) {
        return new BookDto(bookService.removeGenreFromBook(id, genreService.findGenre(genreId)));
    }
    @DeleteMapping("/{id}")
    public BookDto deleteBook(@PathVariable Long id) {
        return new BookDto(bookService.deleteBook(id));
    }
}

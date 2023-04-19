package com.labwork01.app.book.controller;

import com.labwork01.app.WebConfiguration;
import com.labwork01.app.author.service.AuthorService;
import com.labwork01.app.book.service.BookService;
import com.labwork01.app.genre.controller.GenreDto;
import com.labwork01.app.genre.model.Genre;
import com.labwork01.app.genre.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API +"/books")
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
    public BookDto createBook(@RequestBody @Valid BookDto bookDto) {
        List<Genre> genres = new ArrayList<>();
        for (GenreDto obj: bookDto.getGenres()) {
            genres.add(genreService.findGenre(obj.getId()));
        }

        return new BookDto(bookService.addBook(bookDto.getName(), bookDto.getDescription(),
                authorService.findAuthor(bookDto.getAuthor().getId()), genres));
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id,
                              @RequestBody @Valid BookDto bookDto) {
        return new BookDto(bookService.updateBook(id, bookDto.getName(), bookDto.getDescription(),
                authorService.findAuthor(bookDto.getAuthor().getId())));
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

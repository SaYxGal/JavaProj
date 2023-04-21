package com.labwork01.app.book.controller;

import com.labwork01.app.author.controller.AuthorDto;
import com.labwork01.app.author.service.AuthorService;
import com.labwork01.app.book.service.BookService;
import com.labwork01.app.genre.controller.GenreDto;
import com.labwork01.app.genre.model.Genre;
import com.labwork01.app.genre.service.GenreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookMvcController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    public BookMvcController(BookService bookService, AuthorService authorService, GenreService genreService){
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }
    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }
    @GetMapping
    public String getBooks(@RequestParam(value = "authorId", required = false) Long authorId,
                             @RequestParam(value = "genreId",required = false) Long genreId,
                             @RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("books",
                bookService.findAllBooks(authorId, genreId, name).stream()
                        .map(BookDto::new)
                        .toList());
        model.addAttribute("genres", genreService.findAllGenres());
        return "book";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editBook(@PathVariable(required = false) Long id,
                             Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("bookDto", new BookDtoForTL());
            model.addAttribute("selectedAuthor", null);
        } else {
            BookDtoForTL book = new BookDtoForTL(bookService.findBook(id));
            model.addAttribute("selectedAuthor", book.getAuthorId());
            model.addAttribute("bookDto", book);
        }
        model.addAttribute("authors", authorService.findAllAuthors().stream().map(AuthorDto::new).toList());
        model.addAttribute("genres", genreService.findAllGenres().stream().map(GenreDto::new).toList());
        return "book-edit";
    }
    @PostMapping(value = {"", "/{id}"})
    public String saveBook(@PathVariable(required = false) Long id,
                             @ModelAttribute("bookDto") @Valid BookDtoForTL book,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "book-edit";
        }
        List<Genre> genres = new ArrayList<>();
        for (Long obj: book.getGenresId()) {
            genres.add(genreService.findGenre(obj));
        }
        if (id == null || id <= 0) {
            bookService.addBook(book.getName(), book.getDescription(), authorService.findAuthor(book.getAuthorId()),genres);
        }
        else {
            bookService.updateBook(id, book.getName(), book.getDescription(), authorService.findAuthor(book.getAuthorId()));
            List<Genre> currentGenres = bookService.findBook(id).getGenres();
            for(int i = 0; i < genres.size(); i++){
                if(!currentGenres.contains(genres.get(i))){
                    bookService.addGenreToBook(id, genres.get(i));
                }
            }
            currentGenres = bookService.findBook(id).getGenres();
            for(int i = 0; i < currentGenres.size(); i++){
                if(!genres.contains(currentGenres.get(i))){
                    bookService.removeGenreFromBook(id, currentGenres.get(i));
                }
            }
        }
        return "redirect:/books";
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}

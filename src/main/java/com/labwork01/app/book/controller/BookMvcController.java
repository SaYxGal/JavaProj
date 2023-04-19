package com.labwork01.app.book.controller;

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
        return "book";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editBook(@PathVariable(required = false) Long id,
                             Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("bookDto", new BookDto());
        } else {
            model.addAttribute("bookId", id);
            model.addAttribute("bookDto", new BookDto(bookService.findBook(id)));
        }
        return "book-edit";
    }
    @PostMapping(value = {"", "/{id}"})
    public String saveBook(@PathVariable(required = false) Long id,
                             @ModelAttribute("bookDto") @Valid BookDto bookDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "book-edit";
        }
        if (id == null || id <= 0) {
            List<Genre> genres = new ArrayList<>();
            for (GenreDto obj: bookDto.getGenres()) {
                genres.add(genreService.findGenre(obj.getId()));
            }
            bookService.addBook(bookDto.getName(), bookDto.getDescription(), authorService.findAuthor(bookDto.getAuthor().getId()),genres);
        } else {
            bookService.updateBook(id, bookDto.getName(), bookDto.getDescription(), authorService.findAuthor(bookDto.getAuthor().getId()));
        }
        return "redirect:/books";
    }
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}

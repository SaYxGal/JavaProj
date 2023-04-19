package com.labwork01.app.author.controller;

import com.labwork01.app.author.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorMvcController {
    private final AuthorService authorService;
    public AuthorMvcController(AuthorService authorService){
        this.authorService = authorService;
    }
    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }
    @GetMapping
    public String getAuthors(Model model) {
        model.addAttribute("authors",
                authorService.findAllAuthors().stream()
                        .map(AuthorDto::new)
                        .toList());
        return "author";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editAuthor(@PathVariable(required = false) Long id,
                            Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("authorDto", new AuthorDto());
        } else {
            model.addAttribute("authorId", id);
            model.addAttribute("authorDto", new AuthorDto(authorService.findAuthor(id)));
        }
        return "author-edit";
    }
    @PostMapping(value = {"", "/{id}"})
    public String saveAuthor(@PathVariable(required = false) Long id,
                            @ModelAttribute("authorDto") @Valid AuthorDto authorDto,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "author-edit";
        }
        if (id == null || id <= 0) {
            authorService.addAuthor(authorDto.getName(), authorDto.getSurname(), authorDto.getPatronymic());
        } else {
            authorService.updateAuthor(id, authorDto.getName(), authorDto.getSurname(), authorDto.getPatronymic());
        }
        return "redirect:/authors";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}

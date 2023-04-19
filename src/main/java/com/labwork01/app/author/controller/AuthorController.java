package com.labwork01.app.author.controller;

import com.labwork01.app.WebConfiguration;
import com.labwork01.app.author.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API +"/authors")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }
    @GetMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable Long id) {
        return new AuthorDto(authorService.findAuthor(id));
    }

    @GetMapping
    public List<AuthorDto> getAuthors() {
        return authorService.findAllAuthors().stream()
                .map(AuthorDto::new)
                .toList();
    }

    @PostMapping
    public AuthorDto createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        return new AuthorDto(authorService.addAuthor(authorDto.getName(), authorDto.getSurname(), authorDto.getPatronymic()));
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@PathVariable Long id,
                                  @RequestBody @Valid AuthorDto authorDto) {
        return new AuthorDto(authorService.updateAuthor(id, authorDto.getName(), authorDto.getSurname(), authorDto.getPatronymic()));
    }

    @DeleteMapping("/{id}")
    public AuthorDto deleteAuthor(@PathVariable Long id) {
        return new AuthorDto(authorService.deleteAuthor(id));
    }
}

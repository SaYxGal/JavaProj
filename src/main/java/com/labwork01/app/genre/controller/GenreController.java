package com.labwork01.app.genre.controller;

import com.labwork01.app.WebConfiguration;
import com.labwork01.app.genre.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API +"/genres")
public class GenreController {
    private final GenreService genreService;
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }
    @GetMapping("/{id}")
    public GenreDto getGenre(@PathVariable Long id) {
        return new GenreDto(genreService.findGenre(id));
    }

    @GetMapping
    public List<GenreDto> getGenres() {
        return genreService.findAllGenres().stream()
                .map(GenreDto::new)
                .toList();
    }

    @PostMapping
    public GenreDto createGenre(@RequestBody @Valid GenreDto genreDto) {
        return new GenreDto(genreService.addGenre(genreDto.getName()));
    }

    @PutMapping("/{id}")
    public GenreDto updateGenre(@PathVariable Long id,
                                @RequestBody @Valid GenreDto genreDto) {
        return new GenreDto(genreService.updateGenre(id, genreDto.getName()));
    }

    @DeleteMapping("/{id}")
    public GenreDto deleteGenre(@PathVariable Long id) {
        return new GenreDto(genreService.deleteGenre(id));
    }
}

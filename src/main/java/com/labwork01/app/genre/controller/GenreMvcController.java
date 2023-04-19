package com.labwork01.app.genre.controller;

import com.labwork01.app.genre.service.GenreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genres")
public class GenreMvcController {
    private final GenreService genreService;
    public GenreMvcController(GenreService genreService){
        this.genreService = genreService;
    }
    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }
    @GetMapping
    public String getGenres(Model model) {
        model.addAttribute("genres",
                genreService.findAllGenres().stream()
                        .map(GenreDto::new)
                        .toList());
        return "genre";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editGenre(@PathVariable(required = false) Long id,
                              Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("genreDto", new GenreDto());
        } else {
            model.addAttribute("genreId", id);
            model.addAttribute("genreDto", new GenreDto(genreService.findGenre(id)));
        }
        return "genre-edit";
    }
    @PostMapping(value = {"", "/{id}"})
    public String saveGenre(@PathVariable(required = false) Long id,
                            @ModelAttribute("genreDto") @Valid GenreDto genreDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "genre-edit";
        }
        if (id == null || id <= 0) {
            genreService.addGenre(genreDto.getName());
        } else {
            genreService.updateGenre(id, genreDto.getName());
        }
        return "redirect:/genres";
    }

    @PostMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return "redirect:/genres";
    }
}

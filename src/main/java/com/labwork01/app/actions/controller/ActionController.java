package com.labwork01.app.actions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.labwork01.app.actions.service.ActionService;

@RestController
public class ActionController {
    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    //@GetMapping("/")
   /* public String hello(@RequestParam(value = "name", defaultValue = "Мир") String name,
                        @RequestParam(value = "lang", defaultValue = "ru") String lang) {
        return speakerService.sum(name, lang);
    }*/
    @GetMapping("/exact")
    public Boolean exact(@RequestParam(value = "n1", defaultValue = "0") Object num_1, @RequestParam(value = "n2", defaultValue = "0") Object num_2,
                      @RequestParam(value = "type", defaultValue = "int") String type) {
        return actionService.exact(num_1, num_2, type);
    }

    @GetMapping("/sum")
    public String sum(@RequestParam(value = "n1", defaultValue = "0") Object num_1, @RequestParam(value = "n2", defaultValue = "0") Object num_2,
                      @RequestParam(value = "type", defaultValue = "int") String type) {
        return actionService.sum(num_1, num_2, type);
    }

    @GetMapping("/minus")
    public String minus(@RequestParam(value = "n1", defaultValue = "0") Object num_1, @RequestParam(value = "n2", defaultValue = "0") Object num_2,
                      @RequestParam(value = "type", defaultValue = "int") String type) {
        return actionService.minus(num_1, num_2, type);
    }

    @GetMapping("/multiplication")
    public String multiplication(@RequestParam(value = "n1", defaultValue = "0") Object num_1, @RequestParam(value = "n2", defaultValue = "0") Object num_2,
                        @RequestParam(value = "type", defaultValue = "int") String type) {
        return actionService.multiply(num_1, num_2, type);
    }
}

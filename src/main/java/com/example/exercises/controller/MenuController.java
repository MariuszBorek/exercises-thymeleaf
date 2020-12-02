package com.example.exercises.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {

    @GetMapping
    public String redirectToMenu() {
        return "redirect:/menu";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        Integer number = 3;
        String myTxt = "your number is " + number;
        model.addAttribute("number", number);
        model.addAttribute("txt", myTxt);

        String sampleRole = "ADMIN";
        model.addAttribute("role", sampleRole);
        return "menu";
    }

    @GetMapping("/user")
    public String user() {
        return "info-user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "info-admin";
    }

    @GetMapping("hello")
    public String hello(
            @RequestParam(value = "name", defaultValue = "Word", required = true) String name,
            Model model) {
        model.addAttribute("nameFromParam", name);
        return "hello";

    }

}

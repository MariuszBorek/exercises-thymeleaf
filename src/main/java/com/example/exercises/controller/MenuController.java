package com.example.exercises.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String menu() {
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

}

package com.example.exercises.controller;

import com.example.exercises.domain.model.Role;
import com.example.exercises.domain.model.User;
import com.example.exercises.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MenuController {

    private final UserService userService;

    public MenuController(UserService userService) {
        this.userService = userService;
    }

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

    @GetMapping("/hello")
    public String hello(
            @RequestParam(value = "name", defaultValue = "Word", required = true) String name,
            Model model) {
        model.addAttribute("nameFromParam", name);
        return "hello";

    }

    @GetMapping("/user_home")
    public String viewUserHome(
            @AuthenticationPrincipal User user,
            Model model) {
        model.addAttribute("user", user);
        model.addAttribute("admin", new Role("ROLE_ADMIN"));
        return "user/user_home";
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile) {
        try {
            userService.saveImage(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/menu";
    }

}

package com.example.exercises.controller;


import com.example.exercises.domain.forms.UserCreatorForm;
import com.example.exercises.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/create")
    public String getUserCreatorForm(Model model) {
        model.addAttribute("userForm", new UserCreatorForm());
        return "user/create-user";
    }

    @PostMapping("/create")
    public String createUser(
            @Valid @ModelAttribute("userForm") UserCreatorForm userForm,
            BindingResult result,
            Model model) {
        if(!result.hasErrors()) {
            userService.createUser(userForm);
            return "redirect:/menu";
        } else {
            model.addAttribute("userForm", userForm);
            return "user/create-user";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/menu";
    }

    @GetMapping("/edit/{id}")
    public String getUserEditorForm(
            @PathVariable("id") Integer id,
            Model model) {
        model.addAttribute("editForm", userService.findById(id));
        return "user/edit-user";
    }

    @PostMapping("/edit/{id}")
    public String editUser(
            @PathVariable("id") Integer id,
            @ModelAttribute("editForm") UserCreatorForm editForm) {
        userService.updateUser(id, editForm);
        return "redirect:/menu";
    }

}

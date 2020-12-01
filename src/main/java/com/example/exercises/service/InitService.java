package com.example.exercises.service;

import com.example.exercises.domain.forms.UserCreatorForm;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    private final UserService userService;

    public InitService(UserService userService) {
        this.userService = userService;
        init();
    }

    private void init() {
        userService.createUser(new UserCreatorForm("Weronika", "Kowalska","user","qwerty"));
        userService.createUser(new UserCreatorForm("Andrzej", "Lis","user1","qwerty"));
        userService.createAdmin(new UserCreatorForm("Jarek", "Bogocz","admin","qwerty"));
    }
}

package com.example.exercises.service;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class InitService {

    private final UserService userService;

    public InitService(UserService userService) {
        this.userService = userService;
        init();
    }

    private void init() {
        userService.createUser("admin","qaz", Collections.singletonList("ROLE_ADMIN"));
        userService.createUser("admin1","qaz", Collections.singletonList("ROLE_ADMIN"));
        userService.createUser("admin2","qaz", Collections.singletonList("ROLE_ADMIN"));
        userService.createUser("user","qwe", Collections.singletonList("ROLE_USER"));
        userService.createUser("user1","qwe", Collections.singletonList("ROLE_USER"));
        userService.createUser("user2","qwe", Collections.singletonList("ROLE_USER"));
        userService.createUser("user3","qwe", Collections.singletonList("ROLE_USER"));
        userService.createUser("user4","qwe", Collections.singletonList("ROLE_USER"));

    }
}

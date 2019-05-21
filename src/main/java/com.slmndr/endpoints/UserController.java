package com.slmndr.endpoints;

import com.slmndr.entities.User;
import com.slmndr.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/v1/users")
    public Iterable<User> findAllUsers() {
        return this.userService.findAll();
    }
}

package com.slmndr.endpoints;

import com.slmndr.entities.User;
import com.slmndr.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/v1/users")
    public Iterable<User> find() {
        return this.userService.findAll();
    }

    @GetMapping("/v1/users/{username}")
    public User find(@PathVariable(name = "username", required = false) String username) {
        return this.userService.find(username);
    }

    @PostMapping(value = "/v1/users")
    public String save(@RequestBody(required = false) User user) {
        final Boolean bool = this.userService.save(user);
        if (bool)
            // TODO: Send Email to user here
            return "User saved!";
        return "";
    }
}

package com.slmndr.endpoints;

import com.slmndr.entities.User;
import com.slmndr.mail.EmailSender;
import com.slmndr.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final EmailSender emailSender;

    public UserController(final UserService userService, final EmailSender emailSender) {
        this.userService = userService;
        this.emailSender = emailSender;
    }

    @GetMapping("/v1/users")
    public Iterable<User> find() {
        return this.userService.findAll();
    }

    @GetMapping("/v1/users/{username}")
    public User find(@PathVariable(name = "username", required = false) String username) {
        return this.userService.find(username);
    }

    @PostMapping("/v1/users")
    public ResponseEntity save(@RequestBody(required = false) User user) {
        final Boolean bool = this.userService.save(user);
        if (bool) {
            try {
                this.emailSender.sendEmail(user.getEmail());
                return ResponseEntity.ok().build();
            } catch (MessagingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

}

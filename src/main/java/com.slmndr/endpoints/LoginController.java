package com.slmndr.endpoints;

import com.slmndr.entities.User;
import com.slmndr.services.LoginService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Objects;

@RestController
@CrossOrigin("*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/v1/login")
    public ResponseEntity login(@RequestBody LoginContext loginContext) {
        if (isValidLoginContext(loginContext)) {
            try {
                final User user = this.loginService.login(loginContext.getPassword(), loginContext.getPassword());
                return ResponseEntity.ok(Objects.requireNonNullElse(user, false));
            } catch (Exception e) {
                return ResponseEntity.ok(false);
            }
        }

        return ResponseEntity.ok(false);
    }

    private Boolean isValidLoginContext(LoginContext loginContext) {
        return
            this.isValidField(loginContext.getUsername()) &&
                this.isValidField(loginContext.getPassword());
    }

    private Boolean isValidField(String field) {
        return
            !field.contains(";")
                && !field.contains("--")
                && !field.contains(" ")
                && !field.contains("/")
                && !field.contains("\"")
                && !field.contains("\\");
    }

    @Data
    @Getter
    @Setter
    static class LoginContext implements Serializable {
        public String username;
        public String password;
    }
}

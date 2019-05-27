package com.slmndr.endpoints;

import com.slmndr.services.LoginService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/v1/login")
    public ResponseEntity login(@RequestBody LoginContext loginContext) {
        if (isValidLoginContext(loginContext)) {
            final Boolean logged = this.loginService.login(loginContext.getPassword(), loginContext.getPassword());

            if (logged)
                return ResponseEntity.ok(logged);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
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

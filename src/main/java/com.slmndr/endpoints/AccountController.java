package com.slmndr.endpoints;

import com.slmndr.entities.Account;
import com.slmndr.services.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/v1/accounts")
    public Iterable<Account> findAllAccounts() {
        return this.accountService.findAll();
    }
}

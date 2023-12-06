package me.ricky.guides.securityguides.controller;

import me.ricky.guides.securityguides.model.Account;
import me.ricky.guides.securityguides.repository.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/myAccount")
    public Account getAccountDetails(@RequestParam("id") int id) {
        Account account = accountRepository.findByCustomerId(id);
        return account;
    }
}

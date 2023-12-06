package me.ricky.guides.securityguides.controller;

import me.ricky.guides.securityguides.model.AccountTransaction;
import me.ricky.guides.securityguides.repository.AccountTransactionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {
    private final AccountTransactionRepository accountTransactionRepository;

    public BalanceController(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }

    @GetMapping("/myBalance")
    public List<AccountTransaction> getBalance(@RequestParam("id") int id) {
        List<AccountTransaction> accountTransactions = accountTransactionRepository.findByCustomerIdOrderByTransactionDtDesc(id);
        return accountTransactions;
    }
}

package me.ricky.guides.securityguides.controller;

import me.ricky.guides.securityguides.model.Loan;
import me.ricky.guides.securityguides.repository.LoanRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {
    private final LoanRepository loanRepository;

    public LoansController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    @PostAuthorize("hasRole('USER')")
    @GetMapping("/myLoans")
    public List<Loan> getLoans(@RequestParam("id") int id) {
        List<Loan> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        return loans;
    }

}

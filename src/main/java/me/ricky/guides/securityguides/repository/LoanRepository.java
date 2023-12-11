package me.ricky.guides.securityguides.repository;

import me.ricky.guides.securityguides.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    @PreAuthorize("hasRole('USER')")
    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);
}
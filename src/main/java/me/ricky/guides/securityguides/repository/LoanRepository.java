package me.ricky.guides.securityguides.repository;

import me.ricky.guides.securityguides.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
}
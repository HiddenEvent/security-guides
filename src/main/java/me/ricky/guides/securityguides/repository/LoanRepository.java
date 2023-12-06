package me.ricky.guides.securityguides.repository;

import me.ricky.guides.securityguides.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);
}
package me.ricky.guides.securityguides.repository;

import me.ricky.guides.securityguides.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByCustomerId(int customerId);
}
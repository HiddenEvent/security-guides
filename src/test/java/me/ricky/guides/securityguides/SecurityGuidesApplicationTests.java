package me.ricky.guides.securityguides;

import jakarta.transaction.Transactional;
import me.ricky.guides.securityguides.model.Account;
import me.ricky.guides.securityguides.model.Customer;
import me.ricky.guides.securityguides.repository.AccountRepository;
import me.ricky.guides.securityguides.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityGuidesApplicationTests {
    @Autowired
    AccountRepository accountRepository;

    @Transactional
    @Test
    void contextLoads() {
        Account account = accountRepository.findByCustomerId(1);
        Customer customer = account.getCustomer();
        System.out.println(customer.getId());
        System.out.println(customer.getEmail());
    }

}

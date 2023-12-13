package me.ricky.guides.securityguides.controller;

import me.ricky.guides.securityguides.model.Customer;
import me.ricky.guides.securityguides.repository.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class LoginController {
    private final CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Customer customer) {
        Customer savedCustomer;
        customer.setCreateDt(LocalDate.now());
        savedCustomer = customerRepository.save(customer);
        return "User registered successfully with id " + savedCustomer.getId();
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());

        return customers.isEmpty() ? null : customers.get(0);
    }

}

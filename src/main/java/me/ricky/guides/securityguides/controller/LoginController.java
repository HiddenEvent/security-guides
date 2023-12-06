package me.ricky.guides.securityguides.controller;

import me.ricky.guides.securityguides.model.Customer;
import me.ricky.guides.securityguides.repository.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Customer customer) {
        Customer savedCustomer;
        customer.setPwd(passwordEncoder.encode(customer.getPwd()));
        savedCustomer = customerRepository.save(customer);
        return "User registered successfully with id " + savedCustomer.getId();
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());

        return customers.isEmpty() ? null : customers.get(0);
    }

}

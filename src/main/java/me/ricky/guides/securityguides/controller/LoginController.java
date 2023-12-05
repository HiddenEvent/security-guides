package me.ricky.guides.securityguides.controller;

import me.ricky.guides.securityguides.model.Customer;
import me.ricky.guides.securityguides.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}

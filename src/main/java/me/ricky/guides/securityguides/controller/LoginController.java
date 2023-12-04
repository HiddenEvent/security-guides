package me.ricky.guides.securityguides.controller;

import me.ricky.guides.securityguides.model.Customer;
import me.ricky.guides.securityguides.repository.CustomerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final CustomerRepository customerRepository;

    public LoginController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Customer customer) {
        Customer savedCustomer;
        savedCustomer = customerRepository.save(customer);
        return "User registered successfully with id " + savedCustomer.getId();
    }

}

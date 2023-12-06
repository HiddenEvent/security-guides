package me.ricky.guides.securityguides.repository;

import me.ricky.guides.securityguides.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, String> {
}
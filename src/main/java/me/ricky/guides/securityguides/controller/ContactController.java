package me.ricky.guides.securityguides.controller;

import me.ricky.guides.securityguides.model.Contact;
import me.ricky.guides.securityguides.repository.ContactRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contact")
//    @PreFilter("filterObject.contactName != 'Test'")
    @PostFilter("filterObject.contactName != 'Test'")
    public List<Contact> getContact(@RequestBody List<Contact> contacts) {
        Contact contact = contacts.get(0);
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(LocalDate.now());
        contactRepository.save(contact);
        ArrayList<Contact> returnContacts = new ArrayList<>();
        returnContacts.add(contact);
        return returnContacts;
    }

    private String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}

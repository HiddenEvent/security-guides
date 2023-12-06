package me.ricky.guides.securityguides.controller;

import me.ricky.guides.securityguides.model.Card;
import me.ricky.guides.securityguides.repository.CardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {
    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/myCard")
    public List<Card> getCardDetails(@RequestParam("id") int id) {
        List<Card> cards = cardRepository.findByCustomerId(id);
        return cards;
    }
}

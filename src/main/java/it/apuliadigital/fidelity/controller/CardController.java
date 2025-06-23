package it.apuliadigital.fidelity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.apuliadigital.fidelity.model.Card;
import it.apuliadigital.fidelity.sevice.Interface.ICard;
@Validated
@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private ICard cardService;

    // crea una nuova carta fedeltà
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody  Card card) {
        try {
            Card createdCard = cardService.createCard(card);
            return new ResponseEntity<>(createdCard, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    // restituisce tutte le carte fedeltà
    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        if (cards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cards);
    }

    // restituisce una carta fedeltà per id
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id")  Long id) {
        return cardService.getCardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // restituisce una carta fedeltà per codice fiscale
    @GetMapping("/search")
    public ResponseEntity<Card> getCardByCodFisc(@RequestParam("codFisc") String codFisc) {
        return cardService.getCardByCodFisc(codFisc)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // restituisce una carta fedeltà per numero di carta
    @PostMapping("/{id}/add-points")
    public ResponseEntity<Card> addPoints(@PathVariable("id") Long id, @RequestParam("points") int points) {
        return cardService.addPointsToCard(id, points)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // rimuove punti da una carta fedeltà
    @PostMapping("/{id}/remove-points")
    public ResponseEntity<Card> removePoints(@PathVariable("id") Long id, @RequestParam("points") int points) {
        return cardService.removePointsFromCard(id, points)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }


    // restituisce il saldo punti di una carta fedeltà
    @GetMapping("/{id}/balance")
    public ResponseEntity<Card> getCardBalance(@PathVariable("id") Long id) {
        return cardService.getCardBalance(id)
                .map(balance -> ResponseEntity.ok(balance))
                .orElse(ResponseEntity.notFound().build());
    }
}
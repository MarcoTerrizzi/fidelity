
package it.apuliadigital.fidelity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.apuliadigital.fidelity.model.Card;
import it.apuliadigital.fidelity.repository.CardRepository;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardRepository repo;


   //get balance tessere
   @GetMapping("/all")
   public ResponseEntity getAllCards() {
       var cards = repo.findAll();
       if (!cards.iterator().hasNext()) {
           return ResponseEntity.noContent().build();
       } else {
            return ResponseEntity.ok(cards);
        }
    }

    //get balance punti tessera
    @GetMapping("/points/{numTessera}")
    public ResponseEntity getCardById(@PathVariable int numTessera) {
        var card = repo.findById(numTessera);
        if (card.isPresent()) {
            return ResponseEntity.ok(card.get().getBalancePoint());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    //post add card - CREA TESSERA
    @PostMapping("/add/{codFisc}/{balancePunti}")
    public ResponseEntity createCard(@PathVariable String codFisc, @PathVariable int balancePunti) {
        var card = new Card();
        card.setCodFisc(codFisc);
        card.setBalancePoint(balancePunti);
        repo.save(card);
        return ResponseEntity.ok(card);
    }





}
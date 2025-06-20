package it.apuliadigital.fidelity.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.apuliadigital.fidelity.model.Card;
import jakarta.transaction.Transactional;

interface CardRepository extends CrudRepository<Card, Integer> {
}

@Service
@Transactional
public class CardServiceImpl {
    @Autowired
    private CardRepository repository;

    public ResponseEntity<Card> createCard(int balancePunti, String codFisc) {
        Card card = new Card(balancePunti, codFisc);
        repository.save(card);
        java.net.URI location = java.net.URI.create("/cards/" + card.getId());
        return ResponseEntity.created(location).body(card);
    }

    public ResponseEntity<Card> removebalancePoint(int numTessera, int remove) {
        Card card = repository.findById((int) numTessera).orElse(null);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        card.setBalancePoint(card.getBalancePoint() - remove);
        repository.save(card);
        return ResponseEntity.ok(card);
    }

    public ResponseEntity<Card> addbalancePoint(int numTessera, int add) {
        Card card = repository.findById((int) numTessera).orElse(null);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        card.setBalancePoint(card.getBalancePoint() + add);
        repository.save(card);
        return ResponseEntity.ok(card);
    }

}

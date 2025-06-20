package it.apuliadigital.fidelity.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.apuliadigital.fidelity.model.Card;

interface CardRepository extends CrudRepository<Card, Integer> {
}

@Service
public class CardServiceImpl {
    @Autowired
    private CardRepository repository;

    public ResponseEntity<Card> createCard(int saldoPunti, String codFisc) {
        Card card = new Card(saldoPunti, codFisc);
        repository.save(card);
        java.net.URI location = java.net.URI.create("/cards/" + card.getId());
        return ResponseEntity.created(location).body(card);
    }

    public ResponseEntity<Card> removeSaldoPoint(int numTessera, int remove) {
        Card card = repository.findById((int) numTessera).orElse(null);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        card.setSaldoPunti(card.getSaldoPunti() - remove);
        repository.save(card);
        return ResponseEntity.ok(card);
    }

    public ResponseEntity<Card> addSaldoPoint(int numTessera, int add) {
        Card card = repository.findById((int) numTessera).orElse(null);
        if (card == null) {
            return ResponseEntity.notFound().build();
        }
        card.setSaldoPunti(card.getSaldoPunti() + add);
        repository.save(card);
        return ResponseEntity.ok(card);
    }

}

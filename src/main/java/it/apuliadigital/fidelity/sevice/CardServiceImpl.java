package it.apuliadigital.fidelity.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.apuliadigital.fidelity.model.Card;
import it.apuliadigital.fidelity.repository.CardRepository;
import it.apuliadigital.fidelity.sevice.Interface.ICard;

@Service
@Transactional
public class CardServiceImpl implements ICard {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> getCardById(Long cardId) {
        return cardRepository.findById(cardId);
    }

    @Override
    public Card createCard(Card card) {
        // Assume findByCodFisc returns Optional<Card>
        if (cardRepository.findByCodFisc(card.getCodFisc()).isPresent()) {
            throw new IllegalArgumentException("A card with this Cod Fisc already exists.");
        }
        return cardRepository.save(card);
    }

    @Override
    public Optional<Card> addPointsToCard(Long cardId, int pointsToAdd) {
        return cardRepository.findById(cardId).map(card -> {
            card.setBalancePoint(card.getBalancePoint() + pointsToAdd);
            return cardRepository.save(card);
        });
    }

    @Override
    public Optional<Card> removePointsFromCard(Long cardId, int pointsToRemove) {
        return cardRepository.findById(cardId).map(card -> {
            int currentPoints = card.getBalancePoint();
            if (currentPoints >= pointsToRemove) {
                card.setBalancePoint(currentPoints - pointsToRemove);
                return cardRepository.save(card);
            } else {
                // Throw an exception if not enough points
                throw new IllegalArgumentException("Not enough points to remove from the card.");
            }
        });
    }

    @Override
    public Optional<Card> getCardByCodFisc(String codFisc) {
        return cardRepository.findByCodFisc(codFisc);
    }

    @Override
    public Optional<Card> getCardBalance(Long id) {
         throw new UnsupportedOperationException("Unimplemented method 'getCardBalance'");
    }
}
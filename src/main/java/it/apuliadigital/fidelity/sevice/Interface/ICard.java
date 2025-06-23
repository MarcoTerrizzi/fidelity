package it.apuliadigital.fidelity.sevice.Interface;

import java.util.List;
import java.util.Optional;

import it.apuliadigital.fidelity.model.Card;

public interface ICard {

    List<Card> getAllCards();

    Optional<Card> getCardById(Long cardId);

    Card createCard(Card card);

    Optional<Card> addPointsToCard(Long cardId, int pointsToAdd);

    Optional<Card> removePointsFromCard(Long cardId, int pointsToRemove);

    Optional<Card> getCardByCodFisc(String codFisc);

    Optional<Card> getCardBalance(Long id);
}

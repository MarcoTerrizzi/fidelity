package it.apuliadigital.fidelity.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.apuliadigital.fidelity.model.Card;
import it.apuliadigital.fidelity.repository.CardRepository;
import it.apuliadigital.fidelity.sevice.CardServiceImpl;

public class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    private Card card;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        card = new Card();
        card.setCodFisc("ABC123XYZ");
        card.setBalancePoint(100);
    }

    @Test
    public void testCreateCard_Success() {
        when(cardRepository.findByCodFisc(card.getCodFisc())).thenReturn(Optional.empty());
        when(cardRepository.save(card)).thenReturn(card);

        Card created = cardService.createCard(card);

        assertNotNull(created);
        assertEquals("ABC123XYZ", created.getCodFisc());
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    public void testCreateCard_Throws_WhenDuplicate() {
        when(cardRepository.findByCodFisc(card.getCodFisc())).thenReturn(Optional.of(card));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cardService.createCard(card);
        });

        assertEquals("A card with this Cod Fisc already exists.", exception.getMessage());
        verify(cardRepository, never()).save(any());
    }

    @Test
    public void testAddPointsToCard_Success() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(cardRepository.save(any(Card.class))).thenAnswer(i -> i.getArgument(0));

        int pointsToAdd = 50;
        Optional<Card> updatedCardOpt = cardService.addPointsToCard(1L, pointsToAdd);

        assertTrue(updatedCardOpt.isPresent());
        assertEquals(150, updatedCardOpt.get().getBalancePoint());
    }

    @Test
    public void testRemovePointsFromCard_Success() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(cardRepository.save(any(Card.class))).thenAnswer(i -> i.getArgument(0));

        int pointsToRemove = 50;
        Optional<Card> updatedCardOpt = cardService.removePointsFromCard(1L, pointsToRemove);

        assertTrue(updatedCardOpt.isPresent());
        assertEquals(50, updatedCardOpt.get().getBalancePoint());
    }

    @Test
    public void testRemovePointsFromCard_Throws_WhenNotEnoughPoints() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cardService.removePointsFromCard(1L, 150);
        });

        assertEquals("Not enough points to remove from the card.", exception.getMessage());
        verify(cardRepository, never()).save(any());
    }
}

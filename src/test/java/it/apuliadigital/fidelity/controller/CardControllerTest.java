package it.apuliadigital.fidelity.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.apuliadigital.fidelity.model.Card;
import it.apuliadigital.fidelity.sevice.Interface.ICard;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ICard cardService;

    @InjectMocks
    private CardController cardController;

    private ObjectMapper objectMapper;

    private Card exampleCard;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cardController).build();
        objectMapper = new ObjectMapper();

        exampleCard = new Card();
        exampleCard.setCodFisc("ABCDEF12G34H567I");
        exampleCard.setBalancePoint(100);
    }

    @Test
    void testCreateCard() throws Exception {
        when(cardService.createCard(any(Card.class))).thenReturn(exampleCard);

        mockMvc.perform(post("/cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exampleCard)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codFisc").value("ABCDEF12G34H567I"));
    }

    @Test
    void testGetAllCards() throws Exception {
        when(cardService.getAllCards()).thenReturn(Arrays.asList(exampleCard));

        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetCardById() throws Exception {
        when(cardService.getCardById(1L)).thenReturn(Optional.of(exampleCard));

        mockMvc.perform(get("/cards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codFisc").value("ABCDEF12G34H567I"));
    }

    @Test
    void testGetCardByCodFisc() throws Exception {
        when(cardService.getCardByCodFisc("ABCDEF12G34H567I")).thenReturn(Optional.of(exampleCard));

        mockMvc.perform(get("/cards/search?codFisc=ABCDEF12G34H567I"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testAddPoints() throws Exception {
        exampleCard.setBalancePoint(150);
        when(cardService.addPointsToCard(1L, 50)).thenReturn(Optional.of(exampleCard));

        mockMvc.perform(post("/cards/1/add-points?points=50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balancePoint").value(150));
    }

    @Test
    void testRemovePoints() throws Exception {
        exampleCard.setBalancePoint(50);
        when(cardService.removePointsFromCard(1L, 50)).thenReturn(Optional.of(exampleCard));

        mockMvc.perform(post("/cards/1/remove-points?points=50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balancePoint").value(50));
    }

    @Test
    void testGetCardBalance() throws Exception {
        when(cardService.getCardBalance(1L)).thenReturn(Optional.of(exampleCard));

        mockMvc.perform(get("/cards/1/balance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balancePoint").value(100));
    }

    @Test
    void testGetAllCards_NoContent() throws Exception {
        when(cardService.getAllCards()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/cards"))
                .andExpect(status().isNoContent());
    }
}

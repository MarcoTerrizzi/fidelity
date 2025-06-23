package it.apuliadigital.fidelity.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.apuliadigital.fidelity.model.Card;
import it.apuliadigital.fidelity.model.RecordRewards;
import it.apuliadigital.fidelity.model.Rewards;
import it.apuliadigital.fidelity.repository.CardRepository;
import it.apuliadigital.fidelity.repository.RecordRewardsRepository;
import it.apuliadigital.fidelity.repository.RewardsRepository;
import it.apuliadigital.fidelity.sevice.RewardsServiceImpl;

public class RewardsServiceImplTest {

    @Mock
    private RewardsRepository rewardsRepository;

    @Mock
    private RecordRewardsRepository recordRewardsRepository;

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private RewardsServiceImpl rewardsService;

    private Card card;
    private Rewards reward;
    private RecordRewards recordRewards;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        card = new Card();
        card.setBalancePoint(200);

        reward = new Rewards();
        reward.setId(10L);
        reward.setNome("Test Reward");
        reward.setPuntiNecessari(150);

        recordRewards = new RecordRewards(1L, 10L, "Test Reward");
    }

    @Test
    public void testCreateReward() {
        when(rewardsRepository.save(reward)).thenReturn(reward);

        Rewards created = rewardsService.createReward(reward);

        assertNotNull(created);
        assertEquals("Test Reward", created.getNome());
        verify(rewardsRepository, times(1)).save(reward);
    }

    @Test
    public void testGetAllRewards() {
        when(rewardsRepository.findAll()).thenReturn(Arrays.asList(reward));

        List<Rewards> rewards = rewardsService.getAllRewards();

        assertFalse(rewards.isEmpty());
        assertEquals(1, rewards.size());
        verify(rewardsRepository, times(1)).findAll();
    }

    @Test
    public void testGetRewardById_Found() {
        when(rewardsRepository.findById(10L)).thenReturn(Optional.of(reward));

        Optional<Rewards> result = rewardsService.getRewardById(10L);

        assertTrue(result.isPresent());
        assertEquals("Test Reward", result.get().getNome());
        verify(rewardsRepository, times(1)).findById(10L);
    }

    @Test
    public void testGetRewardById_NotFound() {
        when(rewardsRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Rewards> result = rewardsService.getRewardById(999L);

        assertFalse(result.isPresent());
        verify(rewardsRepository, times(1)).findById(999L);
    }

    @Test
    public void testRedeemReward_Success() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(rewardsRepository.findById(10L)).thenReturn(Optional.of(reward));
        when(cardRepository.save(any(Card.class))).thenReturn(card);
        when(recordRewardsRepository.save(any(RecordRewards.class))).thenReturn(recordRewards);

        RecordRewards redeemed = rewardsService.redeemReward(1L, 10L);

        assertNotNull(redeemed);
        assertEquals(50, card.getBalancePoint()); // 200 - 150
        verify(cardRepository, times(1)).save(card);
        verify(recordRewardsRepository, times(1)).save(any(RecordRewards.class));
    }

    @Test
    public void testRedeemReward_NotEnoughPoints() {
        card.setBalancePoint(100); // meno dei punti necessari
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(rewardsRepository.findById(10L)).thenReturn(Optional.of(reward));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            rewardsService.redeemReward(1L, 10L);
        });

        assertEquals("Not enough points to redeem this reward", exception.getMessage());
        verify(cardRepository, never()).save(any());
        verify(recordRewardsRepository, never()).save(any());
    }

    @Test
    public void testGetRedeemedRewardsByCard() {
        when(recordRewardsRepository.findByNumTessera(1L)).thenReturn(Arrays.asList(recordRewards));

        List<RecordRewards> records = rewardsService.getRedeemedRewardsByCard(1L);

        assertEquals(1, records.size());
        verify(recordRewardsRepository, times(1)).findByNumTessera(1L);
    }

    @Test
    public void testGetAllRedeemedRewards() {
        when(recordRewardsRepository.findAll()).thenReturn(Arrays.asList(recordRewards));

        List<RecordRewards> records = rewardsService.getAllRedeemedRewards();

        assertEquals(1, records.size());
        verify(recordRewardsRepository, times(1)).findAll();
    }

    @Test
    public void testGetRewardByName_Found() {
        when(rewardsRepository.findByNome("Test Reward")).thenReturn(Optional.of(reward));

        Optional<Rewards> result = rewardsService.getRewardByName("Test Reward");

        assertTrue(result.isPresent());
        assertEquals("Test Reward", result.get().getNome());
        verify(rewardsRepository, times(1)).findByNome("Test Reward");
    }

    @Test
    public void testGetRewardByName_NotFound() {
        when(rewardsRepository.findByNome("Unknown")).thenReturn(Optional.empty());

        Optional<Rewards> result = rewardsService.getRewardByName("Unknown");

        assertFalse(result.isPresent());
        verify(rewardsRepository, times(1)).findByNome("Unknown");
    }

    @Test
    public void testGetRewardsByPoints() {
        when(rewardsRepository.findByPuntiNecessari(150)).thenReturn(Collections.singletonList(reward));

        List<Rewards> rewards = rewardsService.getRewardsByPoints(150);

        assertEquals(1, rewards.size());
        verify(rewardsRepository, times(1)).findByPuntiNecessari(150);
    }
}

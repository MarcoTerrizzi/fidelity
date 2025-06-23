package it.apuliadigital.fidelity.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.apuliadigital.fidelity.model.RecordRewards;
import it.apuliadigital.fidelity.model.Rewards;
import it.apuliadigital.fidelity.sevice.Interface.IRewards;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRewards rewardsService;

    @Autowired
    private ObjectMapper objectMapper;

    private Rewards sampleReward;
    private RecordRewards sampleRecord;

    @BeforeEach
    void setup() {
        sampleReward = new Rewards();
        sampleReward.setId(1L);
        sampleReward.setNome("Sconto10");
        sampleReward.setPuntiNecessari(100);

        sampleRecord = new RecordRewards(1L, 1L, "Sconto10");
        sampleRecord.setId(1L);
    }

    @Test
    void testCreateReward() throws Exception {
        when(rewardsService.createReward(any(Rewards.class))).thenReturn(sampleReward);

        mockMvc.perform(post("/rewards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleReward)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(sampleReward.getId()))
                .andExpect(jsonPath("$.nome").value(sampleReward.getNome()))
                .andExpect(jsonPath("$.puntiNecessari").value(sampleReward.getPuntiNecessari()));
    }

    @Test
    void testGetAllRewards_withData() throws Exception {
        when(rewardsService.getAllRewards()).thenReturn(Collections.singletonList(sampleReward));

        mockMvc.perform(get("/rewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetAllRewards_noData() throws Exception {
        when(rewardsService.getAllRewards()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/rewards"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetRewardById_found() throws Exception {
        when(rewardsService.getRewardById(1L)).thenReturn(Optional.of(sampleReward));

        mockMvc.perform(get("/rewards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetRewardById_notFound() throws Exception {
        when(rewardsService.getRewardById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/rewards/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetRewardByName_found() throws Exception {
        when(rewardsService.getRewardByName("Sconto10")).thenReturn(Optional.of(sampleReward));

        mockMvc.perform(get("/rewards/search/by-name?name=Sconto10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Sconto10"));
    }

    @Test
    void testGetRewardByName_notFound() throws Exception {
        when(rewardsService.getRewardByName("Invalid")).thenReturn(Optional.empty());

        mockMvc.perform(get("/rewards/search/by-name?name=Invalid"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetRewardsByPoints_found() throws Exception {
        when(rewardsService.getRewardsByPoints(100)).thenReturn(Collections.singletonList(sampleReward));

        mockMvc.perform(get("/rewards/search/by-points?points=100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetRewardsByPoints_notFound() throws Exception {
        when(rewardsService.getRewardsByPoints(999)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/rewards/search/by-points?points=999"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testRedeemReward_success() throws Exception {
        when(rewardsService.redeemReward(1L, 1L)).thenReturn(sampleRecord);

        mockMvc.perform(post("/rewards/redeem?cardId=1&rewardId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeRicompensa").value("Sconto10"));
    }

    @Test
    void testRedeemReward_badRequest() throws Exception {
        when(rewardsService.redeemReward(1L, 99L)).thenThrow(new RuntimeException("Reward not found"));

        mockMvc.perform(post("/rewards/redeem?cardId=1&rewardId=99"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllRedeemedRewards_withData() throws Exception {
        when(rewardsService.getAllRedeemedRewards()).thenReturn(Collections.singletonList(sampleRecord));

        mockMvc.perform(get("/rewards/redeemed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetAllRedeemedRewards_noData() throws Exception {
        when(rewardsService.getAllRedeemedRewards()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/rewards/redeemed"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetRedeemedRewardsByCard_withData() throws Exception {
        when(rewardsService.getRedeemedRewardsByCard(1L)).thenReturn(Collections.singletonList(sampleRecord));

        mockMvc.perform(get("/rewards/redeemed/by-card/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetRedeemedRewardsByCard_noData() throws Exception {
        when(rewardsService.getRedeemedRewardsByCard(2L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/rewards/redeemed/by-card/2"))
                .andExpect(status().isNoContent());
    }
}

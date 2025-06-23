package it.apuliadigital.fidelity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.apuliadigital.fidelity.model.RecordRewards;
import it.apuliadigital.fidelity.model.Rewards;
import it.apuliadigital.fidelity.sevice.Interface.IRewards;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private IRewards rewardsService;

    // crea una nuova ricompensa
    @PostMapping
    public ResponseEntity<Rewards> createReward(@RequestBody Rewards reward) {
        Rewards createdReward = rewardsService.createReward(reward);
        return new ResponseEntity<>(createdReward, HttpStatus.CREATED);
    }

    // restituisce tutte le ricompense
    @GetMapping
    public ResponseEntity<List<Rewards>> getAllRewards() {
        List<Rewards> rewards = rewardsService.getAllRewards();
        if (rewards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rewards);
    }

    // restituisce una ricompensa per id
    @GetMapping("/{id}")
    public ResponseEntity<Rewards> getRewardById(@PathVariable Long id) {
        return rewardsService.getRewardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // restituisce una ricompensa per nome
    @GetMapping("/search/by-name")
    public ResponseEntity<Rewards> getRewardByName(@RequestParam("name") String name) {
        return rewardsService.getRewardByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // restituisce una lista di ricompense per punti
    @GetMapping("/search/by-points")
    public ResponseEntity<List<Rewards>> getRewardsByPoints(@RequestParam("points") int points) {
        List<Rewards> rewards = rewardsService.getRewardsByPoints(points);
        if (rewards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rewards);
    }

    // consente di riscattare una ricompensa
    @PostMapping("/redeem")
    public ResponseEntity<RecordRewards> redeemReward(@RequestParam Long cardId, @RequestParam Long rewardId) {
        try {
            RecordRewards record = rewardsService.redeemReward(cardId, rewardId);
            return ResponseEntity.ok(record);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // restituisce tutte le ricompense riscattate
    @GetMapping("/redeemed")
    public ResponseEntity<List<RecordRewards>> getAllRedeemedRewards() {
        List<RecordRewards> records = rewardsService.getAllRedeemedRewards();
        if (records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }

    // restituisce le ricompense riscattate per id della carta
    @GetMapping("/redeemed/by-card/{cardId}")
    public ResponseEntity<List<RecordRewards>> getRedeemedRewardsByCard(@PathVariable Long cardId) {
        List<RecordRewards> records = rewardsService.getRedeemedRewardsByCard(cardId);
        if (records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }
}

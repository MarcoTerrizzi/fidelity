package it.apuliadigital.fidelity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import it.apuliadigital.fidelity.model.Rewards;
import it.apuliadigital.fidelity.sevice.Interface.IRewards;

public class RewardController {

    @Autowired
    private IRewards service;

    //GET per la lista di premi
    //@Operation
    //@ApiResponses
    @GetMapping("/rewards")
    public ResponseEntity<List<Rewards>> rewards() {
        List<Rewards> rewards = service.readRewards();
        return ResponseEntity.ok(rewards);
    }

    /*
    //POST crea nuovo premio
    @PostMapping("/rewards")
    public ResponseEntity<Rewards> createReward(@RequestBody Rewards rewards) {
        Rewards rewards = service.saveRewards(rewards);
        return ResponseEntity.ok(rewards); 
    }
    */

}
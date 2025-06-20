package it.apuliadigital.fidelity.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.apuliadigital.fidelity.model.Rewards;
import it.apuliadigital.fidelity.repository.RewardsReposity;
import it.apuliadigital.fidelity.sevice.Interface.IRewards;

public class RewardsService implements IRewards {

    @Autowired
    RewardsReposity reposity;

    @Override
    public List<Rewards> readRewards() {
        return (List<Rewards>) reposity.findAll();
    }

    /*
    @Override
    public Rewards saveRewards(Rewards reward) {
        return reposity.save(reward);
    }
    */
}
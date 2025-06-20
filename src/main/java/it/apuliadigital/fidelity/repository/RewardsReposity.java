package it.apuliadigital.fidelity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.apuliadigital.fidelity.model.Rewards;

@Repository
public interface RewardsReposity extends CrudRepository<Rewards, Integer> {

}
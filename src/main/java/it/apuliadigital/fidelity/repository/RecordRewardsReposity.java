package it.apuliadigital.fidelity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.apuliadigital.fidelity.model.RecordRewards;

@Repository
public interface RecordRewardsReposity extends CrudRepository<RecordRewards, Integer> {

}

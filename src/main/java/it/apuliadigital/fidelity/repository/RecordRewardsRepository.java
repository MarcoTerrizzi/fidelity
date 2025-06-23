package it.apuliadigital.fidelity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.apuliadigital.fidelity.model.RecordRewards;

@Repository
public interface RecordRewardsRepository extends JpaRepository<RecordRewards, Long> {
    
    List<RecordRewards> findByNumTessera(Long numTessera);
}